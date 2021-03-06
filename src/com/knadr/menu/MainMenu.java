package com.knadr.menu;

import com.knadr.util.Mode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MainMenu extends BasicGameState {

    public static final int ID = 1;
    private int playersChoice = 0;
    private ArrayList<String> playersOptions = new ArrayList<>();
    private static int NOCHOICES;
    private boolean exit = false;
    private TrueTypeFont playersOptionsTTF;
    private Color notChosen = new Color(153, 204, 255);
    private final String adresseMenu = new String(Files.readAllBytes(Paths.get("res" + File.separator + "fileGame" + File.separator + "menu.json")));
    private final JSONArray menuJson = new JSONObject(adresseMenu).getJSONArray("main menu");
    public static String pseudo = "_";
    public static Mode mode = Mode.ANY;

    public MainMenu() throws IOException, JSONException {}

    public static JSONObject getLanguageJson() {
        try {
            String adresseLanguage = new String(Files.readAllBytes(Paths.get("res" + File.separator + "fileGame" + File.separator + "language.json")));
            return new JSONObject(adresseLanguage);
        }
        catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getLanguage() {
        try {
            String adresseSettings = new String(Files.readAllBytes(Paths.get("res" + File.separator + "fileGame" + File.separator + "settings.json")));
            return new JSONObject(adresseSettings).getJSONObject("settings").getString("language");
        }
        catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray getMenuJson() {
        try {
            String adresseMenu = new String(Files.readAllBytes(Paths.get("res" + File.separator + "fileGame" + File.separator + "menu.json")));
            return new JSONObject(adresseMenu).getJSONArray("main menu");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getID() { return ID; }

    @Override
    public void init(GameContainer gc, StateBasedGame state) throws SlickException {
        try {
            Font font = new Font("Verdana", Font.BOLD, 40);
            playersOptionsTTF = new TrueTypeFont(font, true);
            font = new Font ("Verdana", Font.PLAIN, 20);
            TrueTypeFont foo = new TrueTypeFont(font, true);

            for (int i=0; i < menuJson.length(); i++)
                    if (menuJson.get(i).getClass().getSimpleName().equals("JSONObject"))
                        playersOptions.add(menuJson.getJSONObject(i).names().getString(0));
                    else if (menuJson.get(i).getClass().getSimpleName().equals("String"))
                        playersOptions.add(menuJson.getString(i));

            NOCHOICES = playersOptions.size();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame state, int delta) throws SlickException {
        //int xpos = Mouse.getX();
        //int ypos = Mouse.getY();
        //String mouse = "x: " + xpos + " y: " + ypos;
        //System.out.println(mouse);
        //System.out.println(Mouse.isButtonDown(0));

        Input input = gc.getInput();

        if (input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_BACK)) exit = true;

        if (input.isKeyPressed(Input.KEY_DOWN))
            if (playersChoice == (NOCHOICES - 1)) playersChoice = 0;
            else playersChoice++;

        if (input.isKeyPressed(Input.KEY_UP))
            if (playersChoice == 0) playersChoice = NOCHOICES - 1;
            else playersChoice--;

        if (input.isKeyPressed(Input.KEY_ENTER)) {
            String choix = playersOptions.get(playersChoice);

            switch (choix) {
                case "quit":
                    exit = true;
                    break;
                case "score":
                    state.enterState(ScoreMenu.ID);
                    break;
                case "settings":
                    state.enterState(SettingsMenu.ID);
                    break;
                case "classic mode":
                    mode = Mode.CLASSIC;
                    state.enterState(ClassicLoader.ID);
                    //state.enterState(PseudoMenu.ID);
                    break;
                case "adventure mode":
                    mode = Mode.ADVENTURE;
                    state.enterState(PseudoMenu.ID);
                    break;
                default:
                    System.out.println(choix);
                    break;
            }
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame state, Graphics g) throws SlickException {
        try {
            if (exit)
                gc.exit();

            String adresseLanguage = new String(Files.readAllBytes(Paths.get("res" + File.separator + "fileGame" + File.separator + "language.json")));
            String adresseSettings = new String(Files.readAllBytes(Paths.get("res" + File.separator + "fileGame" + File.separator + "settings.json")));
            JSONObject languageJson = new JSONObject(adresseLanguage);
            String language = new JSONObject(adresseSettings).getJSONObject("settings").getString("language");

            for (int i = 0; i < playersOptions.size(); i++)
                if (playersChoice == i) playersOptionsTTF.drawString(100, i * 50 + 200, languageJson.getJSONObject(language).getString(playersOptions.get(i)));
                else playersOptionsTTF.drawString(100, i * 50 + 200, languageJson.getJSONObject(language).getString(playersOptions.get(i)), notChosen);
        }
        catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }
}