package com.knadr.entitie;

import com.knadr.menu.ClassicLoader;
import com.knadr.util.Etat_Avion;
import com.knadr.map.Map;
import org.newdawn.slick.*;

import java.io.File;

import static com.knadr.util.Etat_Avion.*;

public class Canadair {
    private Etat_Avion Etat = VOL;
    public boolean Decrochage = false;
    public int CoordXDepart = 0, CoordYDepart = ClassicLoader.dimWindowY / 2;
    public int Reserve = 0;

    private float vitesse = 10;
    private float vitesseMin = 5, vitesseMax = 50;
    private SpriteSheet player;
    private Animation playerAnimation;
    public float x = this.CoordXDepart, y = this.CoordYDepart;
    public static float dimPlayerX = 130, dimPlayerY = 55;
    private int direction = 1;
    private boolean moving = false;
    private Map map;
    private GameContainer container;

    public Canadair(Map map) {
        this.map = map;
    }

    public void init(GameContainer container) throws SlickException {
        this.container = container;
        player = new SpriteSheet("res" + File.separator + "img" + File.separator + "player" + File.separator + "skin1.png", 130, 55);
        playerAnimation = new Animation(player, 100);
    }

    public void render() {
        playerAnimation.draw(x, y);
    }

    public void update(int delta) {
        if (this.moving) {
            float futurX = getFuturX((int) vitesse);
            float futurY = getFuturY(delta);
            boolean collision = map.isCollision(futurX, futurY);
            if (collision) {
                //this.moving = false;
                this.Etat = CRASH;
            } else {
                this.x = futurX;
                this.y = futurY;
            }
        }
    }

    public float getFuturX(int delta) {
        return this.x + .1f * delta;
    }

    public float getFuturY(int delta) {
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

    private void changeVitesse(int key) {
        if (key == Input.KEY_LEFT) {
            if (vitesse - 1 >= vitesseMin)
                vitesse--;
        }
        else if (key == Input.KEY_RIGHT) {
            if (vitesse + 1 < vitesseMax)
                vitesse++;
        }
    }

    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_LEFT:
            case Input.KEY_RIGHT:
                changeVitesse(key);
                break;
            case Input.KEY_UP:
                this.direction = 0;
                this.moving = true;
                break;
            case Input.KEY_DOWN:
                this.direction = 2;
                this.moving = true;
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

    public Etat_Avion getEtat() {
        return this.Etat;
    }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    public void setVitesse(float vitesse) { this.vitesse = vitesse; }
    public float getX() { return this.x; }
    public float getY() { return this.y; }
    public float getVitesse() { return this.vitesse; }
}