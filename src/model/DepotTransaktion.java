package model;

/**
 * Document representing buying or selling a stock.
 */
public class DepotTransaktion extends Document {

    private String aktienkurs;
    private String kursWaehrung;
    private String betrag;
    private String betragsWaehrung;
    private String umrechnungskurs;


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

}
