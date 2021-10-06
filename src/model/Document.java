package model;

public abstract class Document {
    private String portfolio;
    private String valutaDatum;
    private OrderType orderType;
    private String verrechneterBetrag;
    private String buchungsWaehrung;

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
}
