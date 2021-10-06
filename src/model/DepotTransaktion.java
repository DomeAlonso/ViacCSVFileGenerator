package model;

/**
 * Document representing buying or selling a stock.
 */
public class DepotTransaktion extends Document {
    private double anzahl;
    private String isin;
    private String aktienkurs;
    private String kursWaehrung;
    private String betrag;
    private String betragsWaehrung;
    private String umrechnungskurs;
    private String steuern;
    private String gebuehren;


    public double getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(double anzahl) {
        this.anzahl = anzahl;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getAktienKurs() {
        return aktienkurs;
    }

    public void setAktienkurs(String aktienkurs) {
        this.aktienkurs = aktienkurs;
    }

    public String getKursWaehrung() {
        return kursWaehrung;
    }

    public void setKursWaehrung(String kursWaehrung) {
        this.kursWaehrung = kursWaehrung;
    }

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

    public String getSteuern() {
        return steuern;
    }

    public void setSteuern(String steuern) {
        this.steuern = steuern;
    }

    public String getGebuehren() {
        return gebuehren;
    }

    public void setGebuehren(String gebuehren) {
        this.gebuehren = gebuehren;
    }
}
