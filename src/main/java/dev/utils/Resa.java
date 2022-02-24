package dev.utils;

public enum Resa {
    ACTIF("ACTIF"),
    ANNULE("ANNULE"),
    ARCHIVE("ARCHIVE");

    private String val;

    Resa(String val){this.val = val;}

    public String getVal(){return this.val;}
}
