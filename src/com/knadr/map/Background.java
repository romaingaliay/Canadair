package com.knadr.map;

import com.knadr.camera.Camera;
import com.knadr.entitie.Canadair;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.File;

import static com.knadr.menu.ClassicLoader.dimWindowX;
import static com.knadr.menu.ClassicLoader.dimWindowY;

public class Background {

    private Canadair player;
    private Camera camera;
    private Map map;
    private Image bgGame;
    private Image bgGame2;
    private Image bgGame3;

    private int width;
    private float vitesseParallax = .1f;
    private float vitesseParallax2 = .2f;
    
    public Background(Canadair player, Camera camera, Map map) {
        this.player = player;
        this.camera = camera;
        this.map = map;
    }
    
    public void init() throws SlickException {
        width = this.map.getMap().getWidth() * this.map.getMap().getTileHeight();
        bgGame = new Image("res" + File.separator + "img" + File.separator + "map" + File.separator + "background.png");
        bgGame2 = new Image("res" + File.separator + "img" + File.separator + "map" + File.separator + "2_background.png");
        bgGame3 = new Image("res" + File.separator + "img" + File.separator + "map" + File.separator + "3_background.png");
    }
    
    public void render() throws SlickException {
        bgGame.draw(camera.xCamera - dimWindowX / 2 - 5, camera.yCamera - dimWindowY / 2);

        for (int x = -bgGame2.getWidth(); x < width; x += bgGame2.getWidth())
            bgGame2.draw(x + player.x - camera.vitesse * vitesseParallax, 0);

        for (int x = -bgGame3.getWidth(); x < width; x += bgGame3.getWidth())
            bgGame3.draw(x + player.x - camera.vitesse * vitesseParallax2, 0);
    }
}
