package com.knadr.util;

public enum Etat_Feu {
    ALLUME("Le feu est toujours allumé"),
    ETEINT("Le feu est éteint"),
    FIN_JEU("Fin du Game");

    private String txt;

    Etat_Feu(String txt){
        this.txt = txt;
    }

    @Override
    public String toString() { return txt; }
}
