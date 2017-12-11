package menu;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import player.Canadair;
import map.Map;

import java.io.File;

public class ClassicLoader extends BasicGameState {

    public static final int ID = 6;

    private GameContainer container;
    private Map map = new Map();
    private Canadair player = new Canadair(map);


    private Image bgGame;
    private Image bgGame2;
    private Image bgGame3;


    private static int dimWindowX, dimWindowY;
    private float xCamera = 640, yCamera = 360;

    @Override
    public int getID() { return ID; }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.container = gameContainer;
        dimWindowX = container.getWidth();
        dimWindowY = container.getHeight();

        this.player.init();
        this.map.init();

        bgGame = new Image("res" + File.separator + "img" + File.separator + "map" + File.separator + "background.png");
        bgGame2 = new Image("res" + File.separator + "img" + File.separator + "map" + File.separator + "2_background.png");
        bgGame3 = new Image("res" + File.separator + "img" + File.separator + "map" + File.separator + "3_background.png");

    }

    @Override
    public void update(GameContainer gc, StateBasedGame state, int delta) throws SlickException {
        this.player.update(delta);

        updateCamera(gc);

        Input input = gc.getInput();

        if (input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_BACK)) state.enterState(MainMenu.ID);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        g.translate(container.getWidth() / 2 - (int) xCamera, container.getHeight() / 2 - (int) yCamera);

        bgGame.draw(0,0);
        bgGame2.draw(0,0);
        bgGame3.draw(0,0);

        this.player.render(g);
        this.map.render();
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {

    }

    private void updateCamera(GameContainer container) {
        this.xCamera = this.player.x + Canadair.dimPlayerX / 2;

        /*int h = container.getHeight() / 4;
        if (this.player.y> this.yCamera + h) {
            this.yCamera = this.player.y - h;
        } else if (this.player.y< this.yCamera - h) {
            this.yCamera = this.player.y + h;
        }*/
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
