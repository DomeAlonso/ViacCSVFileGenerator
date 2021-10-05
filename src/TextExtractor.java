import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextExtractor {

    String text;

    public TextExtractor(String text){
        this.text = text;
    }

    /**
     * Valuta
     */
    public String getDatum() throws Exception {
        String pattern = "(?<=Valuta )\\d{2}.\\d{2}.\\d{4}";
        return searchRegex(pattern);
    }

    /**
     * Order
     */
    public String getTyp() throws Exception {
        String pattern = "(?<=Order: )\\w*";
        String typ = searchRegex(searchRegex(pattern));
        if (typ.equals("Kauf")){
            return "Kauf";
        }
        else if (typ.equals("Verkauf")){
            return "Verkauf";
        }
        else{
            throw new Exception("Order typ "+typ+" not supported.");
        }
    }

    /**
     * Verrechneter Betrag (CHF)
     */
    public String getWert() throws Exception {
        String pattern = "(?<=Verrechneter Betrag: Valuta .{15})[0-9'.]*";
        return searchRegex(pattern);
    }

    /**
     * Währung Verrechneter Betrag (CHF)
     * @return
     */
    public String getBuchungswaehrung() throws Exception {
        String pattern = "(?<=Verrechneter Betrag: Valuta .{11})\\w{3}";
        return searchRegex(pattern);
    }

    /**
     * Betrag (In Fondswährung)
     */
    public String getBruttobetrag() throws Exception {
        String pattern = "(?<=Betrag.{5})[0-9'.]*";
        return searchRegex(pattern);
    }

    /**
     * Währung des Betrag (Fondswährung)
     */
    public String getWaehrungBruttobetrag() throws Exception {
        String pattern = "(?<=Betrag )\\w*";
        return searchRegex(pattern);
    }

    /**
     * Umrechungskurs
     */
    public String getWechselkurs() throws Exception {
        String pattern = "(?<=Umrechnungskurs.{9})[0-9'.]*";
        return searchRegex(pattern);
    }

    public String getGebühren(){
        //TODO taxes
        return "";
    }

    public String getSteuern(){
        //TODO taxes
        return "";
    }

    /**
     * Kurs
     */
    public String getKurs() throws Exception {
        String pattern = "(?<=Kurs: .{3} )[0-9'.]*";
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
    public String getISIN() throws Exception {
        String pattern = "(?<=ISIN: )([A-Z]{2})([A-Z0-9]{10})";
        return searchRegex(pattern);
    }

    /**
     * Last two digits of portfolio
     */
    public String getPortfolio() throws Exception {
        String pattern ="(?<=Portfolio.{15})\\d*";
        return searchRegex(pattern);
    }



    private String searchRegex(String pattern) throws Exception {
        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(text);
        if (m.find()){
            return m.group(0);
        }else{
            throw new Exception("Regex not found");
        }
    }



}
