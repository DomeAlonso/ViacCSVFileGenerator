package model;

public class KontoTransaktionDividende extends KontoTransaktion{

    private String betrag;
    private String betragsWaehrung;
    private String umrechnungskurs;
    private String ausschuettung;
    private String ausschuettungWaehrung;


    public String getBetrag() {
        return betrag;
    }

    public void setBetrag(String betrag) {
        this.betrag = betrag;
    }

    public String getBetragsWaehrung() {
        return betragsWaehrung;
    }

    public void setBetragsWaehrung(String betragsWaehrung) {
        this.betragsWaehrung = betragsWaehrung;
    }

    public String getUmrechnungskurs() {
        return umrechnungskurs;
    }

    public void setUmrechnungskurs(String umrechnungskurs) {
        this.umrechnungskurs = umrechnungskurs;
    }

    public String getAusschuettung() {
        return ausschuettung;
    }

    public void setAusschuettung(String ausschuettung) {
        this.ausschuettung = ausschuettung;
    }

    public String getAusschuettungWaehrung() {
        return ausschuettungWaehrung;
    }

    public void setAusschuettungWaehrung(String ausschuettungWaehrung) {
        this.ausschuettungWaehrung = ausschuettungWaehrung;
    }
}
