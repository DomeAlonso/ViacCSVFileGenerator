import model.DepotTransaktion;
import model.KontoTransaktion;

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
            if (portfolio.equals("Abrechnungskonto")){
                fileWriter.write("Datum,Typ,Wert,Buchungswährung,Steuern,Stück,ISIN,Notiz");
            }else{
                fileWriter.write("Datum,Typ,Wert,Buchungswährung,Bruttobetrag,Währung Bruttobetrag,Wechselkurs,Gebühren,Steuern,Stück,ISIN,Notiz");
            }
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

    public void writeKontoTransaction(KontoTransaktion doc, String notiz) {
        StringJoiner joiner = new StringJoiner(",");
        try{
            joiner.
                    add(doc.getValutaDatum()).
                    add(doc.getOrderType().toString()).
                    add(doc.getVerrechneterBetrag()).
                    add(doc.getBuchungsWaehrung()).
                    add(doc.getSteuern()).
                    add(String.valueOf(doc.getAnzahl())).
                    add(doc.getIsin()).
                    add(notiz+"Generiert von VIAC PDF Modifier");

            fileWriter.write(joiner.toString().replaceAll("null",""));
            fileWriter.write("\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}