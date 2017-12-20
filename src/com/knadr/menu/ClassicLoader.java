package com.knadr.menu;

import com.knadr.camera.Camera;
import com.knadr.entitie.Canadair;
import com.knadr.map.Background;
import com.knadr.map.Map;
import com.knadr.map.Trigger;
import com.knadr.util.Etat_Avion;
import com.knadr.util.Mode;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import static com.knadr.menu.MainMenu.mode;

public class ClassicLoader extends BasicGameState {

    public static final int ID = 9;

    private int level;
    private GameContainer container;
    public float xCamera = 640, yCamera = 360;
    private Map map;
    private Canadair player;
    //private Trigger trigger;
    private Camera camera;
    private Background background;
    public static int dimWindowX, dimWindowY;

    @Override
    public int getID() { return ID; }

    @Override
    public void init(GameContainer gc, StateBasedGame state) throws SlickException {
        this.container = gc;
        dimWindowX = container.getWidth();
        dimWindowY = container.getHeight();

        this.map = new Map();
        this.player = new Canadair(map);
        this.camera = new Camera(xCamera, yCamera, player);
        this.background = new Background(player, camera, map);
        //this.trigger = new Trigger(map, player);

        this.player.init(gc);
        this.map.init();
        this.camera.init(gc);
        this.background.init();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame state, int delta) throws SlickException {
        this.player.update(delta);
        this.camera.update(delta);
        //this.trigger.update();

        /**trigger*/
        if (this.player.getFuturX((int) this.player.getVitesse()) > (477-22)*30) {
            this.player.setX(0);
            this.player.setY(this.player.getY());
        }
        /***/

        Input input = gc.getInput();

        if (input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_BACK)) {
            mode = Mode.ANY;
            gc.reinit();
            state.enterState(MainMenu.ID);
        }

        if (this.player.getEtat().equals(Etat_Avion.CRASH) || this.player.getEtat().equals(Etat_Avion.SUBMERGE)) {
            mode = Mode.ANY;
            gc.reinit();
            state.enterState(MainMenu.ID);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        this.camera.render(g);
        this.background.render();
        this.map.renderBackground();
        this.player.render();
        this.map.renderForeground();
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
