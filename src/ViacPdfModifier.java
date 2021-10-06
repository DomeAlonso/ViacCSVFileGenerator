import model.DepotTransaktion;
import model.Document;

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
       portfolios.add(new CSVWriter("Abrechnungskonto"));
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

       //Proccess PDF
       for (File file: pdfFiles) {
           try{
               System.out.println("Handling file: "+file.getAbsolutePath()+"\n");
               pdfText = VIACReader.readFile(file);
               TextExtractor extractor = new TextExtractor(pdfText);
               Document document = extractor.getdocument();
               portfolios.get(Integer.parseInt(extractor.getPortfolio())).writeDepotTransaction((DepotTransaktion) document);

              /* int index = Integer.parseInt(extractor.getPortfolio());

               portfolios.get(index).writeFile(
                       extractor.getDatum(),
                       extractor.getTyp(),
                       extractor.getWert(),
                       extractor.getBuchungswaehrung(),
                       extractor.getBruttobetrag(),
                       extractor.getWaehrungBruttobetrag(),
                       extractor.getWechselkurs(),
                       extractor.getGebühren(),
                       extractor.getSteuern(),
                       extractor.calculateStueck(Double.parseDouble(extractor.getBruttobetrag()),Double.parseDouble(extractor.getKurs().replaceAll("'",""))),
                       extractor.getISIN()
               );*/
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
