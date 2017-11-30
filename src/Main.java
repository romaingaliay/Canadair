import menu.*;
import org.json.JSONException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;

public class Main extends StateBasedGame {

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Main(), 1280, 720, false);
        app.setVSync(false);
        app.setShowFPS(false);
        app.start();
    }

    public Main() { super("JCanadair"); }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        try {
            addState(new MainMenu());
            addState(new ScoreMenu());
            addState(new SettingMenu());
            addState(new ScoreClassic());
            addState(new ScoreAdventure());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
