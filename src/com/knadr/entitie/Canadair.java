package com.knadr.entitie;

import com.knadr.menu.ClassicLoader;
import com.knadr.util.Etat_Avion;
import com.knadr.map.Map;
import org.newdawn.slick.*;

import static com.knadr.util.Etat_Avion.*;
import static com.knadr.util.SkinSelected.skinCanadairSelected;

public class Canadair {
    private Etat_Avion Etat = VOL;
    public boolean Decrochage = false;
    public int Reserve = 0;
    private int maxReserve = 650;
    public int CoordXDepart = 0, CoordYDepart = ClassicLoader.dimWindowY / 2;

    private float acceleration = 0;
    private float vitesse = 5;
    private float vitesseVerticale;
    private int vitesseMin = 1, vitesseMax = 15;
    public float x = this.CoordXDepart, y = this.CoordYDepart;
    public static float dimPlayerX = 130, dimPlayerY = 55;
    private int direction = 1;
    private SpriteSheet player;
    private Animation playerAnimation;
    private Map map;
    private GameContainer container;
    private float angle;

    public Canadair(Map map) {
        this.map = map;
    }

    public void init(GameContainer container) throws SlickException {
        this.container = container;
        player = new SpriteSheet(skinCanadairSelected(), 130, 55);
        playerAnimation = new Animation(player, 100);
    }

    public void render() {
        if (this.direction == 1)
            playerAnimation.draw(x, y);

        if (this.direction == 0) {
            angle = -1.5f - getVitesse() / 2;
            playerAnimation.getImage(0).rotate(angle);
            playerAnimation.getImage(1).rotate(angle);
            playerAnimation.getImage(2).rotate(angle);
            playerAnimation.getImage(3).rotate(angle);
            playerAnimation.draw(x, y);
            playerAnimation.getImage(0).rotate(-angle);
            playerAnimation.getImage(1).rotate(-angle);
            playerAnimation.getImage(2).rotate(-angle);
            playerAnimation.getImage(3).rotate(-angle);
        }

        if (this.direction == 2) {
            angle = 2.5f + getVitesse() / 2;
            playerAnimation.getImage(0).rotate(angle);
            playerAnimation.getImage(1).rotate(angle);
            playerAnimation.getImage(2).rotate(angle);
            playerAnimation.getImage(3).rotate(angle);
            playerAnimation.draw(x, y);
            playerAnimation.getImage(0).rotate(-angle);
            playerAnimation.getImage(1).rotate(-angle);
            playerAnimation.getImage(2).rotate(-angle);
            playerAnimation.getImage(3).rotate(-angle);
        }
    }

    public void update(int delta) {
        float futurX = getFuturX(delta);
        float futurY = getFuturY(delta);

        if (this.y < 0)
            this.y = 0;

        if (map.isCollision(futurX, futurY))
            this.Etat = CRASH;
        else if (map.isSubmerger(futurY))
            this.Etat = SUBMERGE;
        else if (map.isInWater(futurX, futurY)) {
            System.out.println("in water");
            remplissageReservoir();
            this.x = futurX;
            this.y = futurY;
        }
        else if (map.isTooHigh(futurY)) {
            this.x = futurX;
        }
        else {
            this.x = futurX;
            this.y = futurY;
        }
    }

    public float getFuturX(int delta) {
        if (this.acceleration < 0)
            if (this.vitesse + this.acceleration * delta * .001f >= vitesseMin)
                this.vitesse += this.acceleration * delta * .001f;

        if (this.acceleration > 0)
            if (this.vitesse + this.acceleration * delta * .001f < vitesseMax)
                this.vitesse += this.acceleration * delta * .001f;

        return this.x + this.vitesse * .1f * delta;
    }

    public float getFuturY(int delta) {
        float futurY = this.y;
        this.vitesseVerticale = this.vitesse / 6;

        switch (this.direction) {
            case 0:
                this.vitesseVerticale -= 64 * delta * .001f;
                futurY = this.y - this.vitesseVerticale * .1f * delta;
                break;
            case 2:
                this.vitesseVerticale += 64 * delta * .001f;
                futurY = this.y + this.vitesseVerticale * .1f * delta;
                break;
        }
        return futurY;
    }

    private void remplissageReservoir() {

    }

    private void vidageReservoir() {

    }

    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_LEFT:
                this.acceleration = -1;
                break;
            case Input.KEY_RIGHT:
                this.acceleration = 1;
                break;
            case Input.KEY_UP:
                this.direction = 0;
                break;
            case Input.KEY_DOWN:
                this.direction = 2;
                break;
        }
    }

    public void keyReleased(int key, char c) {
        switch (key) {
            case Input.KEY_LEFT:
            case Input.KEY_RIGHT:
                this.acceleration = 0;
                break;
            case Input.KEY_UP:
                this.direction = 1;
                break;
            case Input.KEY_DOWN:
                this.direction = 1;
                break;
        }
    }

    public void setEtat(Etat_Avion etat) { this.Etat = etat; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    public void setVitesse(float vitesse) { this.vitesse = vitesse; }
    public void setAcceleration(float acceleration) { this.acceleration = acceleration; }
    public Etat_Avion getEtat() { return this.Etat; }
    public float getX() { return this.x; }
    public float getY() { return this.y; }
    public float getVitesse() { return this.vitesse; }
    public float getAcceleration() { return this.acceleration; }
}