import model.DepotTransaktion;
import model.KontoTransaktion;
import model.KontoTransaktionDividende;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.StringJoiner;

/**
 * Class creating and writing to csv files
 */
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

    public void writeTransaction(DepotTransaktion doc, String notiz) {
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
                    add(notiz +"Generiert von VIAC CSV Generator am "+ DateTimeFormatter.ofPattern("dd.MM.YYYY").format(LocalDateTime.now()));

            fileWriter.write(joiner.toString().replaceAll("null",""));
            fileWriter.write("\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeTransaction(KontoTransaktion doc, String notiz) {
        StringJoiner joiner = new StringJoiner(",");
        try{
            joiner.
                    add(doc.getValutaDatum()).
                    add(doc.getOrderType().toString()).
                    add(doc.getVerrechneterBetrag()).
                    add(doc.getBuchungsWaehrung()).
                    add("").
                    add("").
                    add("").
                    add("").
                    add(doc.getSteuern()).
                    add(String.valueOf(doc.getAnzahl())).
                    add(doc.getIsin()).
                    add(notiz +"Generiert von VIAC CSV Generator am "+ DateTimeFormatter.ofPattern("dd.MM.YYYY").format(LocalDateTime.now()));

            fileWriter.write(joiner.toString().replaceAll("null",""));
            fileWriter.write("\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeTransaction(KontoTransaktionDividende doc,String notiz){
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
                    add(notiz +"Generiert von VIAC CSV Generator am "+ DateTimeFormatter.ofPattern("dd.MM.YYYY").format(LocalDateTime.now()));

            fileWriter.write(joiner.toString().replaceAll("null",""));
            fileWriter.write("\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
