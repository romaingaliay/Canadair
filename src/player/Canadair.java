package player;

import org.newdawn.slick.*;
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

    public void init() throws SlickException {

    }

    private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }

    public void render(GameContainer gc, Graphics g) {

    }

    public void update(int delta) {

    }
}
