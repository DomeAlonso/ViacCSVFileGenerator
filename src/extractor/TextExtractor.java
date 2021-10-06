package extractor;

import model.DepotTransaktion;
import model.Document;
import model.KontoTransaktion;
import model.OrderType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextExtractor {
    String text;
    OrderType typ;

    public TextExtractor(String text) throws Exception {
        this.text = text;
        typ = getTyp();

        if (typ == null){
            throw new Exception("Order typ not supported");
        }
    }

    /**
     * Valuta
     */
    public String getDatum() {
        String pattern;
        if (getTyp().equals(OrderType.ZINS)){
            pattern = "(?<=Am )\\d{2}.\\d{2}.\\d{4}(?= haben wir )";
        }else {
            pattern = "(?<=Valuta )\\d{2}.\\d{2}.\\d{4}";
        }
        return searchRegex(pattern);
    }

    /**
     * Order
     */
    public OrderType getTyp() {
        String pattern = "(?<=Order: )\\w*";
        String typ = searchRegex(pattern);

        if(typ != null){
            if (typ.equals("Kauf")){
                return OrderType.KAUF;
            }
            else if (typ.equals("Verkauf")){
                return OrderType.VERKAUF;
            }
        }else{
            pattern = "(?<=Basel, .{10}\\r\\n).*";
            typ = searchRegex(pattern);
            if (typ!=null) {
                if (typ.equals("Einzahlung 3a")){
                    return OrderType.EINLAGE;
                }else if(typ.equals("Zins")){
                    return OrderType.ZINS;
                }
            }
        }
        return null;
    }

    /**
     * Verrechneter Betrag (CHF)
     */
    public String getVerrechneterBetrag() {
        String pattern = "(?<=Valuta .{15})[0-9'.]*";
        return searchRegex(pattern);
    }

    /**
     * Währung Verrechneter Betrag (CHF)
     * @return
     */
    public String getBuchungswaehrung() {
        String pattern = "(?<=Valuta .{11})\\w{3}";
        return searchRegex(pattern);
    }

    /**
     * Betrag (In Fondswährung)
     */
    public String getBruttobetrag() {
        String pattern = "(?<=Betrag.{5})[0-9'.]*";
        return searchRegex(pattern);
    }

    /**
     * Währung des Betrag (Fondswährung)
     */
    public String getWaehrungBruttobetrag(){
        String pattern = "(?<=Betrag )\\w*";
        return searchRegex(pattern);
    }

    /**
     * Umrechungskurs
     */
    public String getWechselkurs() {
        String pattern = "(?<=Umrechnungskurs.{9})[0-9'.]*";
        return searchRegex(pattern);
    }

    public String getGebuehren(){
        String pattern = "(?<=Gebühren.{5})[0-9'.]*";
        return "";
    }

    public String getSteuern(){
        String pattern = "(?<=Stempelsteuer.{5})[0-9'.]*";
        return searchRegex(pattern);
    }

    /**
     * Kurs
     */
    public String getAktienKurs()  {
        String pattern = "(?<=Kurs: .{3} )[0-9'.]*";
        return searchRegex(pattern);
    }

    public String getKursWaehrung()  {
        String pattern = "(?<=Kurs: )\\w{3}";
        return searchRegex(pattern);
    }

    /**
     * Calculated amount based on getBruttobetrag() and getKurs()
     */
    public double calculateStueck(double betrag, double kurs){
        return betrag/kurs;
    }

    /**
     * ISIN
     */
    public String getISIN(){
        String pattern = "(?<=ISIN: )([A-Z]{2})([A-Z0-9]{10})";
        return searchRegex(pattern);
    }

    /**
     * Last two digits of portfolio
     */
    public String getPortfolio() {
        String pattern ="(?<=Portfolio.{15})\\d*";
        return searchRegex(pattern);
    }



    String searchRegex(String pattern) {
        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(text);
        if (m.find()){
            return m.group(0);
        }else{
            return null;
        }
    }

    private double getAnzahl(){
        return Double.parseDouble(getBruttobetrag().replaceAll("'","")) /
                Double.parseDouble(getAktienKurs().replaceAll("'",""));
    }


    public Document getdocument() {
        Document document;
        switch(typ){
            case KAUF:
            case VERKAUF:
                document = new DepotTransaktion();
                ((DepotTransaktion) document).setOrderType(typ);
                ((DepotTransaktion) document).setPortfolio(getPortfolio());
                ((DepotTransaktion) document).setIsin(getISIN());
                ((DepotTransaktion) document).setAktienkurs(getAktienKurs());
                ((DepotTransaktion) document).setKursWaehrung(getKursWaehrung());
                ((DepotTransaktion) document).setBetrag(getBruttobetrag());
                ((DepotTransaktion) document).setBetragsWaehrung(getWaehrungBruttobetrag());
                ((DepotTransaktion) document).setUmrechnungskurs(getWechselkurs());
                ((DepotTransaktion) document).setSteuern((getSteuern()));
                ((DepotTransaktion) document).setGebuehren((getGebuehren()));
                ((DepotTransaktion) document).setValutaDatum(getDatum());
                ((DepotTransaktion) document).setVerrechneterBetrag(getVerrechneterBetrag());
                ((DepotTransaktion) document).setBuchungsWaehrung(getBuchungswaehrung());
                ((DepotTransaktion) document).setAnzahl(getAnzahl());
                break;
            case EINLAGE:
            case ZINS:
                document = new KontoTransaktion();
                document.setOrderType(typ);
                document.setPortfolio(getPortfolio());
                document.setValutaDatum(getDatum());
                document.setVerrechneterBetrag(getVerrechneterBetrag());
                document.setBuchungsWaehrung(getBuchungswaehrung());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + typ);
        }

        return document;
    }
}