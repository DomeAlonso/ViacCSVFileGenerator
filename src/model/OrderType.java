package model;

public enum OrderType {
    KAUF("Kauf"),
    VERKAUF("Verkauf"),
    EINLAGE("Einlage");

    private String typ;
    OrderType(String typ) {
        this.typ = typ;
    }

    public String toString(){
        return typ;
    }
}
