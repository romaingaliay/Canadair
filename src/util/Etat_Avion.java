package util;

public enum Etat_Avion {
    VOL("L'avion est en vol"),
    CRASH("L'avion est crashé"),
    SUBMERGE("L'avion est submergé"),
    NEXT_LEVEL("Prochain niveau"),
    FIN_JEU("Fin du Game");

    private String txt;

    Etat_Avion(String txt){
        this.txt = txt;
    }

    @Override
    public String toString() { return txt; }
}
