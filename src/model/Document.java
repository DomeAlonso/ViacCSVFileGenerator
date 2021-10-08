package model;

public abstract class Document {
    private String portfolio;
    private String valutaDatum;
    private float anzahl;
    private OrderType orderType;
    private String verrechneterBetrag;
    private String buchungsWaehrung;
    private String steuern;
    private String gebuehren;
    private String isin;

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public String getValutaDatum() {
        return valutaDatum;
    }

    public void setValutaDatum(String valutaDatum) {
        this.valutaDatum = valutaDatum;
    }

    public double getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(float anzahl) {
        this.anzahl = anzahl;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public String getVerrechneterBetrag() {
        return verrechneterBetrag;
    }

    public void setVerrechneterBetrag(String verrechneterBetrag) {
        this.verrechneterBetrag = verrechneterBetrag;
    }

    public String getBuchungsWaehrung() {
        return buchungsWaehrung;
    }

    public void setBuchungsWaehrung(String buchungsWaehrung) {
        this.buchungsWaehrung = buchungsWaehrung;
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

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }
}
