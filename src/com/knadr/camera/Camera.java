package com.knadr.camera;

import com.knadr.entitie.Canadair;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Camera {

    public float vitesse;
    public float xCamera, yCamera;
    private GameContainer container;
    private Canadair player;

    public Camera(float xCamera, float yCamera, Canadair player) {
        this.xCamera = xCamera;
        this.yCamera = yCamera;
        this.player = player;
    }

    public void init(GameContainer container) {
        this.container = container;
    }

    public void update(int delta) throws SlickException {
        this.xCamera = this.player.x + Canadair.dimPlayerX / 2;

        vitesse = this.player.x + this.player.getFuturX(delta);
        /*int h = container.getHeight() / 4;
        if (this.com.knadr.entitie.y> this.yCamera + h) {
            this.yCamera = this.com.knadr.entitie.y - h;
        } else if (this.com.knadr.entitie.y< this.yCamera - h) {
            this.yCamera = this.com.knadr.entitie.y + h;
        }*/
    }

    public void render(Graphics g) throws SlickException {
        g.translate(container.getWidth() / 2 - (int) xCamera, container.getHeight() / 2 - (int) yCamera);
    }
}
