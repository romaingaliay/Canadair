package com.knadr.entitie;

import com.knadr.util.Etat_Avion;
import com.knadr.map.Map;
import org.newdawn.slick.*;

import java.io.File;

import static com.knadr.util.Etat_Avion.VOL;

public class Canadair {
    public Etat_Avion Etat = VOL;
    public boolean Decrochage = false;
    public int CoordYDepart = 107;
    public int Reserve = 0;
    public int Monte = 0;
    public int Avance = 0;

    private float vitesse = 5;
    private SpriteSheet player;
    private Animation playerAnimation;
    public float x = 0, y = 360;
    public static float dimPlayerX = 130, dimPlayerY = 55;
    private int direction = 1;
    private boolean moving = false;
    private Map map;

    public Canadair(Map map) {
        this.map = map;
    }

    public void init() throws SlickException {
        player = new SpriteSheet("res" + File.separator + "img" + File.separator + "player" + File.separator + "skin1.png", 130, 55);
        playerAnimation = new Animation(player, 100);
    }

    private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
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
                this.moving = false;
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