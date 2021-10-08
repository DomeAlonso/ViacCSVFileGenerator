package extractor;

import model.DepotTransaktion;
import model.Document;
import model.KontoTransaktion;
import model.KontoTransaktionDividende;

public class TextExtractorDividende extends TextExtractor{

    public TextExtractorDividende(String text) throws Exception {
        super(text);
    }

    @Override
    public float getAnzahl(){
        String pattern = ".*(?= Ant )";
        return Float.parseFloat(super.searchRegex(pattern));
    }

    public String getAusschuettung(){
        String pattern = "(?<=Ausschüttung: .{3} )[0-9'.]*";
        return super.searchRegex(pattern);
    }

    public String getAusschuettungWaehrung(){
        String pattern = "(?<=Ausschüttung: )[0-9'.]*";
        return super.searchRegex(pattern);
    }

    @Override
    public Document getdocument(){
        Document document;
        document = new KontoTransaktionDividende();
        ((KontoTransaktionDividende) document).setOrderType(typ);
        ((KontoTransaktionDividende) document).setPortfolio(getPortfolio());
        ((KontoTransaktionDividende) document).setAnzahl(getAnzahl());
        ((KontoTransaktionDividende) document).setIsin(getISIN());
        ((KontoTransaktionDividende) document).setAusschuettung(getAusschuettung());
        ((KontoTransaktionDividende) document).setAusschuettungWaehrung(getAusschuettungWaehrung());
        ((KontoTransaktionDividende) document).setBetrag(getBruttobetrag());
        ((KontoTransaktionDividende) document).setBetragsWaehrung(getWaehrungBruttobetrag());
        ((KontoTransaktionDividende) document).setUmrechnungskurs(getWechselkurs());
        ((KontoTransaktionDividende) document).setSteuern((getSteuern()));
        ((KontoTransaktionDividende) document).setGebuehren((getGebuehren()));
        ((KontoTransaktionDividende) document).setValutaDatum(getDatum());
        ((KontoTransaktionDividende) document).setVerrechneterBetrag(getVerrechneterBetrag());
        ((KontoTransaktionDividende) document).setBuchungsWaehrung(getBuchungswaehrung());
        return document;
    }
}
