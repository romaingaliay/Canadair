package player;

import util.Etat_Avion;

import static util.Etat_Avion.VOL;

public class Canadair {
    public Etat_Avion Etat = VOL;
    public boolean Decrochage = false;
    public int CoordYDepart = 107;
    public int Reserve = 0;
    public int Monte = 0;
    public int Avance = 0;
    public double Vitesse = 20;

    public Canadair() {

    }
}
