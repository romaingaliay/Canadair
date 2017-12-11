package player;

import org.newdawn.slick.*;
import util.Etat_Avion;

import java.io.File;
import map.Map;

import static util.Etat_Avion.VOL;

public class Canadair {
    public Etat_Avion Etat = VOL;
    public boolean Decrochage = false;
    public int CoordYDepart = 107;
    public int Reserve = 0;
    public int Monte = 0;
    public int Avance = 0;
    //public double Vitesse = 20;

    private float vitesse = 5;
    private SpriteSheet player;
    private Animation playerAnimation;
    public float x = 0, y = 360;
    public static float dimPlayerX = 260, dimPlayerY = 110;
    private int direction = 1;
    private boolean moving = false;
    private Map map;

    public Canadair(Map map) {
        this.map = map;
    }

    public void init() throws SlickException {
        player = new SpriteSheet("res" + File.separator + "img" + File.separator + "player" + File.separator + "skin1.png", 260, 110);
        playerAnimation = new Animation(player, 100);
    }

    private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }

    public void render(Graphics g) {
        playerAnimation.draw(x, y);
    }

    public void update(int delta) {
        if (this.moving) {
            float futurX = getFuturX((int) vitesse);
            float futurY = getFuturY(delta);
            boolean collision = map.isCollision(futurX, futurY);
            if (collision) {
                this.moving = false;
            } else {
                this.x = futurX;
                this.y = futurY;
            }
        }
    }

    private float getFuturX(int delta) {
        return this.x + .1f * delta;
    }

    private float getFuturY(int delta) {
        float futurY = this.y;
        switch (this.direction) {
            case 0:
                futurY = this.y - .1f * delta;
                break;
            case 2:
                futurY = this.y + .1f * delta;
                break;
        }
        return futurY;
    }

    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_UP:
                this.direction = 0;
                this.moving = true;
                break;
            case Input.KEY_LEFT:
                this.vitesse--;
                break;
            case Input.KEY_DOWN:
                this.direction = 2;
                this.moving = true;
                break;
            case Input.KEY_RIGHT:
                this.vitesse++;
                break;
        }
    }

    public void keyReleased(int key, char c) {
        switch (key) {
            case Input.KEY_UP:
                this.direction = 1;
                this.moving = true;
                break;
            case Input.KEY_DOWN:
                this.direction = 1;
                this.moving = true;
                break;
        }
    }
}