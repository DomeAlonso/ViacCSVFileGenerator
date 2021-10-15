import model.DepotTransaktion;
import model.Document;
import model.KontoTransaktionDividende;
import org.apache.commons.validator.routines.ISINValidator;

import java.text.SimpleDateFormat;

/**
 * Class responsible of doing some basic checks on Document Object to verify pdf file was read correct.
 */
public class Validator {

    public static boolean validate(Document doc) throws Exception {
        if (doc.getOrderType() == null){
            throw new Exception("Order typ not found.");
        }
        if (doc.getPortfolio() == null){
            throw new Exception("Portfolio not found.");
        }
        if (doc.getValutaDatum() == null){
            throw new Exception("Date not found");
        }else{
            //Check if valid date. Throws ParseException
            new SimpleDateFormat("dd.MM.yyyy").parse(doc.getValutaDatum());
        }
        if (doc.getVerrechneterBetrag() == null){
            throw new Exception("Verrechneter Betrag not found");
        }else{
            //Checks if valid number. Throws NumberFormatException "For input String"
            Double.parseDouble(doc.getVerrechneterBetrag());
        }
        if (doc.getBuchungsWaehrung() == null || doc.getBuchungsWaehrung().length() != 3){
            throw new Exception("Error parsing Buchungswährung");
        }

        if (doc.getSteuern() != null && doc.getSteuern() != ""){
            //Checks if valid number. Throws NumberFormatException "For input String"
            Double.parseDouble(doc.getSteuern());
        }
        if (doc.getGebuehren() != null && doc.getGebuehren() != ""){
            //Checks if valid number. Throws NumberFormatException "For input String"
            Double.parseDouble(doc.getGebuehren());
        }

        if(doc.getAnzahl()<0){
            throw new Exception("Stückanzahl ist kleiner 0");
        }

        if (doc instanceof DepotTransaktion){
            DepotTransaktion depot = (DepotTransaktion) doc;

            //If Order is not in Swiss francs check value in foreign currency is set.
            if (!depot.getBuchungsWaehrung().equals("CHF")){
                if (depot.getUmrechnungskurs() == null || depot.getBetrag() == null || depot.getBetragsWaehrung() == null){
                    throw new Exception("Foreign currency values not found");
                }
            }

            if (depot.getIsin() == null){
                throw new Exception("ISIN not found.");
            }else{
                if(!ISINValidator.getInstance(true).isValid(depot.getIsin())){
                    throw new Exception("ISIN not valid");
                }
            }

            if (depot.getAktienKurs() == null){
                throw new Exception("Aktienkurs not found");
            }else{
                //Checks if valid number. Throws NumberFormatException "For input String"
                Double.parseDouble(depot.getAktienKurs());

                if (!depot.getBetragsWaehrung().equals(depot.getKursWaehrung())){
                    throw new Exception("Kurswährung und Betragswährung unterschiedlich.");
                }
            }
        }


        if(doc instanceof KontoTransaktionDividende){
            KontoTransaktionDividende depot = (KontoTransaktionDividende) doc;

            //If Order is not in Swiss francs check value in foreign currency is set.
            if (!depot.getBuchungsWaehrung().equals("CHF")){
                if (depot.getUmrechnungskurs() == null || depot.getBetrag() == null || depot.getBetragsWaehrung() == null){
                    throw new Exception("Foreign currency values not found");
                }
            }

            if (depot.getIsin() == null){
                throw new Exception("ISIN not found.");
            }else{
                if(!ISINValidator.getInstance(true).isValid(depot.getIsin())){
                    throw new Exception("ISIN not valid");
                }
            }

            if(depot.getAusschuettung() == null){
                throw new Exception("Ausschütungsbetrag nicht gefunden");
            }else{
                //Checks if valid number. Throws NumberFormatException "For input String"
                Double.parseDouble(depot.getAusschuettung());
            }

            if (depot.getAusschuettungWaehrung() == null || depot.getAusschuettungWaehrung().length() != 3){
                throw new Exception("Error parsing Buchungswährung");
            }

            if (depot.getDividendenart() == null || depot.getDividendenart().equals("")){
                throw new Exception("Error parsing Dividendenart.");
            }
        }
        return true;
    }
}
