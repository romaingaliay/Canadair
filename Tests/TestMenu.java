import java.awt.Font;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;

public class TestMenu extends BasicGame {

    private int playersChoice = 0;
    private static final int NOCHOICES = 5;
    private static final int START = 0;
    private static final int SAVE = 1;
    private static final int LOAD = 2;
    private static final int OPTIONS = 3;
    private static final int QUIT = 4;
    private String[] playersOptions = new String[NOCHOICES];
    private boolean exit = false;
    private TrueTypeFont playersOptionsTTF;
    private Color notChosen = new Color(153, 204, 255);

    private TestMenu() {
        super("Slick2D Menu Example");
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        Font font = new Font("Verdana", Font.BOLD, 40);
        playersOptionsTTF = new TrueTypeFont(font, true);
        font = new Font ("Verdana", Font.PLAIN, 20);
        TrueTypeFont foo = new TrueTypeFont(font, true);
        playersOptions[0] = "Start";
        playersOptions[1] = "Save";
        playersOptions[2] = "Load";
        playersOptions[3] = "Options";
        playersOptions[4] = "Quit";
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        String mouse = "x: " + xpos + " y: " + ypos;
        //System.out.println(mouse);
        //System.out.println(Mouse.isButtonDown(0));

        Input input = gc.getInput();

        if (input.isKeyPressed(Input.KEY_DOWN)) {
            if (playersChoice == (NOCHOICES - 1)) {
                playersChoice = 0;
            } else {
                playersChoice++;
            }
        }
        if (input.isKeyPressed(Input.KEY_UP)) {
            if (playersChoice == 0) {
                playersChoice = NOCHOICES - 1;
            } else {
                playersChoice--;
            }
        }
        if (input.isKeyPressed(Input.KEY_ENTER)) {
            switch (playersChoice) {
                case QUIT:
                    exit = true;
                    break;
                case START:
                    break;
            }
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        renderPlayersOptions();
        if (exit) {
            gc.exit();
        }
    }

    public static void main(String[] args)
            throws SlickException {
        AppGameContainer app =
                new AppGameContainer(new TestMenu());
        app.setDisplayMode(800, 600, false);
        app.start();
    }

    private void renderPlayersOptions() {
        for (int i = 0; i < NOCHOICES; i++) {
            if (playersChoice == i) {
                playersOptionsTTF.drawString(100, i * 50 + 200, playersOptions[i]);
            } else {
                playersOptionsTTF.drawString(100, i * 50 + 200, playersOptions[i], notChosen);
            }
        }
    }
}