import model.DepotTransaktion;

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

    public void writeDepotTransaction(String date, String typ, String wert, String buchungswaehrung, String bruttobetrag, String waehrungBruttobetrag, String wechselkurs, String gebühren, String steuern, double amount, String isin) {
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

    public void writeDepotTransaction(DepotTransaktion doc) {
        StringJoiner joiner = new StringJoiner(",");
        try{
            joiner.
                    add(doc.getValutaDatum()).
                    add(doc.getOrderType().toString()).
                    add(doc.getVerrechneterBetrag()).
                    add(doc.getBuchungsWaehrung()).
                    add(doc.getBetrag()).
                    add(doc.getBetragsWaehrung()).
                    add(doc.getUmrechnungskurs()).
                    add(doc.getGebuehren()).
                    add(doc.getSteuern()).
                    add(String.valueOf(doc.getAnzahl())).
                    add(doc.getIsin()).
                    add("Generiert von VIAC PDF Modifier");

            fileWriter.write(joiner.toString().replaceAll("null",""));
            fileWriter.write("\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
