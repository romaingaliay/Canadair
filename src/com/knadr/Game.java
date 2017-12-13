package com.knadr;

import com.knadr.menu.*;
import org.json.JSONException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;

public class Game extends StateBasedGame {

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game(), 1280, 720, false);
        app.setVSync(false);
        app.setShowFPS(true);
        app.start();
    }

    private Game() { super("JCanadair"); }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        try {
            addState(new MainMenu());       // ID = 1
            addState(new ScoreMenu());      // ID = 2
            addState(new SettingsMenu());   // ID = 3
            addState(new ScoreClassic());   // ID = 4
            addState(new ScoreAdventure()); // ID = 5
            addState(new KeyBoardMenu());   // ID = 6
            addState(new SkinMenu());       // ID = 7
            addState(new PseudoMenu());     // ID = 8
            addState(new ClassicLoader());  // ID = 9
            addState(new AdventureLoader());// ID = 10
            addState(new GameOver());       // ID = 11
        }
        catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
