package model;

/**
 * Enum containing the different document/transaction Types.
 */
public enum OrderType {
    KAUF("Kauf"),
    VERKAUF("Verkauf"),
    EINLAGE("Einlage"),
    ZINS("Zinsen"),
    VERWALTUNGSGEBUEHREN("Gebühren"),
    DIVIDENDE("Dividende");


    private String typ;
    OrderType(String typ) {
        this.typ = typ;
    }

    public String toString(){
        return typ;
    }
}
