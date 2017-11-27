import org.newdawn.slick.*;

import java.io.File;

public class TestLib extends BasicGame {
    private GameContainer container;
    private Image bgMenu;
    private Image bgGame;
    private Image player;

    public TestLib() {
        super("Canadair");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        container.setShowFPS(false);
        bgMenu = new Image("res" + File.separator + "img" + File.separator + "menu" + File.separator + "background_menu.png");
        bgGame = new Image("res" + File.separator + "img" + File.separator + "map" + File.separator + "background.png");
        player = new Image("res" + File.separator + "img" + File.separator + "player" + File.separator + "skin1.png");
    }

    @Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        bgGame.draw(0,0);
        player.draw(0,107);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    }

    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new TestLib(), 600, 400, false).start();
    }
}

