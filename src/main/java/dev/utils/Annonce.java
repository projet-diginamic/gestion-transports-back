package dev.utils;

public enum Annonce {
    OUVERT("OUVERT"),
    ANNULE("ANNULE"),
    ARCHIVE("ARCHIVE");

    private String val;

    Annonce(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

}
