package com.knadr.menu;

import com.knadr.camera.Camera;
import com.knadr.entitie.Canadair;
import com.knadr.menu.MainMenu;
import com.knadr.map.Map;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;

public class ClassicLoader extends BasicGameState {

    public static final int ID = 6;

    private GameContainer container;
    private Map map = new Map();
    private Canadair player = new Canadair(map);

    public float xCamera = 640, yCamera = 360;
    private Camera camera = new Camera(xCamera, yCamera, player);

    private Image bgGame;
    private Image bgGame2;
    private Image bgGame3;
    private float vitesseParallax = .25f;
    private float vitesseParallax2 = 1.5f;

    private static int dimWindowX, dimWindowY;

    @Override
    public int getID() { return ID; }

    @Override
    public void init(GameContainer gc, StateBasedGame state) throws SlickException {
        this.container = gc;
        dimWindowX = container.getWidth();
        dimWindowY = container.getHeight();

        this.player.init();
        this.map.init();
        this.camera.init(gc);

        bgGame = new Image("res" + File.separator + "img" + File.separator + "map" + File.separator + "background.png");
        bgGame2 = new Image("res" + File.separator + "img" + File.separator + "map" + File.separator + "2_background.png");
        bgGame3 = new Image("res" + File.separator + "img" + File.separator + "map" + File.separator + "3_background.png");

    }

    @Override
    public void update(GameContainer gc, StateBasedGame state, int delta) throws SlickException {
        this.player.update(delta);
        this.camera.update(delta);

        Input input = gc.getInput();

        if (input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_BACK)) state.enterState(MainMenu.ID);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        this.camera.render(g);

        bgGame.draw(this.camera.xCamera - dimWindowX / 2, this.camera.yCamera - dimWindowY / 2);
        bgGame2.draw(this.player.x - vitesseParallax - this.camera.vitesse * .1f, 0);
        bgGame3.draw(this.player.x - vitesseParallax2 - this.camera.vitesse * .2f, 0);

        this.map.render();
        this.player.render();
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {

    }

    @Override
    public void keyPressed(int key, char c) {
        this.player.keyPressed(key, c);
    }

    @Override
    public void keyReleased(int key, char c) {
        this.player.keyReleased(key, c);
    }
}
