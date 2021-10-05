import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;

public class CSVWriter {

    FileWriter fileWriter = null;

    public CSVWriter(String portfolio){
        try {
            File csvFile = new File("Portfolio "+portfolio+".csv");
            fileWriter = new FileWriter(csvFile);
            fileWriter.write("Datum,Typ,Wert,Buchungswährung,Bruttobetrag,Währung Bruttobetrag,Wechselkurs,Gebühren,Steuern,Stück,ISIN,Notiz");
            fileWriter.write("\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(String date, String typ, String wert, String buchungswaehrung, String bruttobetrag, String waehrungBruttobetrag, String wechselkurs, String gebühren, String steuern, double amount, String isin) {
        try{
            StringJoiner joiner = new StringJoiner(",");
            joiner.add(date).add(typ).add(wert).add(buchungswaehrung).add(bruttobetrag).add(waehrungBruttobetrag).add(wechselkurs).add(gebühren).add(steuern).add(String.valueOf(amount)).add(isin).add("Generiert von VIAC PDF modifier");
            fileWriter.write(joiner.toString());
            fileWriter.write("\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
