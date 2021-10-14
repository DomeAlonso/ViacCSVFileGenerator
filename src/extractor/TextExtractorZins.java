package extractor;

import model.Document;
import model.KontoTransaktion;

public class TextExtractorZins extends TextExtractor {

    public TextExtractorZins(String text) throws Exception {
        super(text);
    }


    /**
     * Verrechneter Betrag (CHF)
     */
    @Override
    public String getVerrechneterBetrag() {
        String pattern = "(?<=Verrechneter Betrag: .{4})[0-9'.]*";
        String result = searchRegex(pattern);
        return super.formatNumber(result);
    }

    /**
     * Währung Verrechneter Betrag (CHF)
     */
    @Override
    public String getBuchungswaehrung() {
        String pattern = "(?<=Verrechneter Betrag: ).{3}";
        return super.searchRegex(pattern);
    }

    @Override
    public Document getdocument(){
        Document document;
        document = new KontoTransaktion();
        document.setOrderType(typ);
        document.setPortfolio(getPortfolio());
        document.setValutaDatum(getDatum());
        document.setVerrechneterBetrag(getVerrechneterBetrag());
        document.setBuchungsWaehrung(getBuchungswaehrung());
        return document;
    }
}
