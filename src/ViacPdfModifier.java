import extractor.TextExtractor;
import extractor.TextExtractorDividende;
import extractor.TextExtractorZins;
import model.DepotTransaktion;
import model.Document;
import model.KontoTransaktion;
import model.KontoTransaktionDividende;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViacPdfModifier {

   public static void main(String args[]) throws IOException {

       StringBuilder errorString = new StringBuilder();
       String pdfText = null;

       //Initialize CSV Export
       ArrayList<CSVWriter> portfolios = new ArrayList<>();
       portfolios.add(new CSVWriter("Referenzkonto"));
       for (int i = 1; i <= 5; i++) {
           portfolios.add(new CSVWriter("0"+i));
       }

       //Get PDFs in directory
       System.out.println(new File(".").getAbsoluteFile());
       File[] files = new File(".").listFiles();
       System.out.println(files[0].getAbsoluteFile());
       //File[] files = new File("src/main/java/").listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null.
       List<File> pdfFiles = new ArrayList<>();
       for (File file : files) {
           if (file.isFile() && file.getName().contains(".pdf")) {
               pdfFiles.add(file);
           }
       }

       //For debugging purposes PDF's can be printed to console.
       if(args.length >0){
           if (args[0].toLowerCase().equals("debug")) {
               for (File pdfFile : pdfFiles) {
                   System.out.println("Handling file: " + pdfFile.getAbsolutePath() + "\n");
                   System.out.println(VIACReader.readFile(pdfFile));
               }
               return;
           }
       }


       //Proccess PDF
       for (File file: pdfFiles) {
           try{
               System.out.println("Handling file: "+file.getAbsolutePath()+"\n");
               pdfText = VIACReader.readFile(file);
               TextExtractor extractor = new TextExtractor(pdfText);
               Document document;

               switch (extractor.getTyp()){
                   case VERKAUF:
                   case KAUF:
                       document = extractor.getdocument();
                       Validator.validate(document);
                       portfolios.get(Integer.parseInt(document.getPortfolio())).writeTransaction((DepotTransaktion) document,"");
                       break;
                   case EINLAGE:
                       document = extractor.getdocument();
                       Validator.validate(document);
                       portfolios.get(0).writeTransaction((KontoTransaktion) document, "Einlage Portfolio "+document.getPortfolio()+". ");
                       break;
                   case ZINS:
                       document = new TextExtractorZins(pdfText).getdocument();
                       Validator.validate(document);
                       portfolios.get(0).writeTransaction((KontoTransaktion) document, "Zins Portfolio "+document.getPortfolio()+". ");
                       break;
                   case VERWALTUNGSGEBUEHREN:
                       document = extractor.getdocument();
                       Validator.validate(document);
                       portfolios.get(0).writeTransaction((KontoTransaktion) document, "Verwaltungsgebühren Portfolio "+document.getPortfolio()+". ");
                       break;
                   case DIVIDENDE:
                       document = new TextExtractorDividende(pdfText).getdocument();
                       Validator.validate(document);
                       portfolios.get(0).writeTransaction((KontoTransaktionDividende) document, ((KontoTransaktionDividende) document).getDividendenart() +" Portfolio "+document.getPortfolio()+". ");
                       break;
               }

           }catch (Exception e){
                errorString = errorString.append("Failed to parse file "+file.getName() +" ("+e.getMessage()+")\n");
               System.out.println("Parsed file:\n"+ pdfText+"\n\n");
                e.printStackTrace();
           }
       }

       new Output(errorString.toString());


       System.out.println("\n\n\n\n\n\nFinished");

   }
}
