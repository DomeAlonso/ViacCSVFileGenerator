package model;

public enum OrderType {
    KAUF("Kauf"),
    VERKAUF("Verkauf");

    private String typ;
    OrderType(String typ) {
        this.typ = typ;
    }

    public String toString(){
        return typ;
    }
}
