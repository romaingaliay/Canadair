package com.knadr;

import com.knadr.menu.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Game extends StateBasedGame {

    public static void main(String[] args) throws SlickException {
        try {
            String adresseSettings = new String(Files.readAllBytes(Paths.get("res" + File.separator + "fileGame" + File.separator + "settings.json")));
            JSONObject settingsJson = new JSONObject(adresseSettings).getJSONObject("settings");
            int widht = Integer.parseInt(settingsJson.getString("resolution").split("[*]")[0]);
            int height= Integer.parseInt(settingsJson.getString("resolution").split("[*]")[1]);
            AppGameContainer app = new AppGameContainer(new Game(), widht, height, settingsJson.getBoolean("full screen"));
            app.setVSync(settingsJson.getBoolean("v sync"));
            app.setShowFPS(settingsJson.getBoolean("show fps"));
            app.start();
        }
        catch (JSONException | IOException e) {
            e.printStackTrace();
        }
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
