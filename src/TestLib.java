import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import java.io.File;

public class TestLib /*extends BasicGame*/ {
    /*private GameContainer container;
    private TiledMap map;
    private SpriteSheet player;
    private Animation playerAnimation;
    private Image bgGame;
    private Image bgGame2;
    private Image bgGame3;

    private static int dimWindowX = 1280, dimWindowY = 720;
    private float dimPlayerX = 260, dimPlayerY = 110;
    private float x = 0, y = 107;
    private int direction = 2;
    private boolean moving = false;

    public TestLib() {
        super("JKnadR");
    }

    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new TestLib(), dimWindowX, dimWindowY, false).start();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        //this.map = new TiledMap("res" + File.separator + "img" + File.separator + "map" + File.separator + "knadrMap.tmx",
        //        "res" + File.separator + "img" + File.separator + "map" + File.separator + "objets");

        container.setShowFPS(false);
        bgGame = new Image("res" + File.separator + "img" + File.separator + "map" + File.separator + "background.png");
        bgGame2 = new Image("res" + File.separator + "img" + File.separator + "map" + File.separator + "2_background.png");
        bgGame3 = new Image("res" + File.separator + "img" + File.separator + "map" + File.separator + "3_background.png");
        player = new SpriteSheet("res" + File.separator + "img" + File.separator + "player" + File.separator + "skin1.png", 260, 110);
        playerAnimation = new Animation(player, 100);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        bgGame.draw(0,0);
        bgGame2.draw(0,0);
        bgGame3.draw(0,0);
        playerAnimation.draw(x,y);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        playerAnimation.update(delta);
        updateCharacter(delta);
    }

    private void updateCharacter(int delta) throws SlickException {
        if (this.moving) {
            float futurX = getFuturX(delta);
            float futurY = getFuturY(delta);
            boolean collision = isCollision(futurX, futurY);
            if (collision) {
               this.moving = false;
            }
            else {
                this.x = futurX;
                this.y = futurY;
            }
        }
    }

    private boolean isCollision(float futurX, float futurY) {
        if (futurX < 0)
            return true;
        if (futurX + dimPlayerX > dimWindowX)
            return true;
        if (futurY < 0)
            return true;
        if (futurY + dimPlayerY > dimWindowY)
            return true;
        return false;
    }

    private float getFuturX(int delta) {
        float futurX = this.x;
        switch (this.direction) {
            case 1:
                futurX = this.x - .1f * delta;
                break;
            case 3:
                futurX = this.x + .1f * delta;
                break;
        }
        return futurX;
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

    @Override
    public void keyReleased(int key, char c) {
        this.moving = false;
        if (Input.KEY_ESCAPE == key) {
            this.container.exit();
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_UP:
                this.direction = 0;
                this.moving = true;
                break;
            case Input.KEY_LEFT:
                this.direction = 1;
                this.moving = true;
                break;
            case Input.KEY_DOWN:
                this.direction = 2;
                this.moving = true;
                break;
            case Input.KEY_RIGHT:
                this.direction = 3;
                this.moving = true;
                break;
        }
    }*/
}

