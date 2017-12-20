package com.knadr.menu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

import static com.knadr.menu.MainMenu.getLanguage;
import static com.knadr.menu.MainMenu.getLanguageJson;
import static com.knadr.util.Find.findKeyArray;
import static com.knadr.util.JSON.chercheKeyJSONArray;

public class KeyBoardMenu extends BasicGameState {

    public static final int ID = 6;

    private GameContainer container;
    private int playersChoice = 0;
    private ArrayList<String> playersOptions = new ArrayList<>();
    private static int NOCHOICES;
    private TrueTypeFont playersOptionsTTF;
    private Color notChosen = new Color(153, 204, 255);
    private ArrayList<String> action = new ArrayList<>();
    private ArrayList<String> nameKeyboard = new ArrayList<>();
    private JSONArray settingsJson = Objects.requireNonNull(MainMenu.getMenuJson()).getJSONObject(chercheKeyJSONArray(Objects.requireNonNull(MainMenu.getMenuJson()),"settings")).getJSONArray("settings");
    private JSONArray keyboardJson;

    public KeyBoardMenu() throws JSONException { }

    @Override
    public int getID() { return ID; }

    @Override
    public void init(GameContainer gc, StateBasedGame state) throws SlickException {
        try {
            String adresseSetting = new String(Files.readAllBytes(Paths.get("res" + File.separator + "fileGame" + File.separator + "settings.json")));
            JSONArray keyboardsJson = new JSONObject(adresseSetting).getJSONArray("keyboard");
            this.container = gc;
            java.awt.Font font = new java.awt.Font("Verdana", java.awt.Font.BOLD, 40);
            playersOptionsTTF = new TrueTypeFont(font, true);

            if (findKeyArray(settingsJson, "keyboard mapping") != -1)
                keyboardJson = settingsJson.getJSONObject(findKeyArray(settingsJson, "keyboard mapping")).getJSONArray("keyboard mapping");

            for (int i=0; i < keyboardsJson.length(); i++) {
                action.add(keyboardsJson.getJSONObject(i).getString("action"));
                nameKeyboard.add(keyboardsJson.getJSONObject(i).getString("name"));
                playersOptions.add(keyboardsJson.getJSONObject(i).getString("action"));
            }

            for (int i=0; i < keyboardJson.length(); i++)
                if (keyboardJson.get(i).getClass().getSimpleName().equals("JSONObject"))
                    playersOptions.add(keyboardJson.getJSONObject(i).names().getString(0));
                else if (keyboardJson.get(i).getClass().getSimpleName().equals("String"))
                    playersOptions.add(keyboardJson.getString(i));

            NOCHOICES = playersOptions.size();

        }
        catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame state, int delta) throws SlickException {
        Input input = gc.getInput();

        if (input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_BACK))
            state.enterState(MainMenu.ID);

        if (input.isKeyPressed(Input.KEY_DOWN))
            if (playersChoice == (NOCHOICES - 1)) playersChoice = 0;
            else playersChoice++;

        if (input.isKeyPressed(Input.KEY_UP))
            if (playersChoice == 0) playersChoice = NOCHOICES - 1;
            else playersChoice--;

        if (input.isKeyPressed(Input.KEY_ENTER)) {
            String choix = playersOptions.get(playersChoice);

            switch (choix) {
                case "return":
                    state.enterState(SettingsMenu.ID);
                    break;
                default:
                    if (action.contains(choix))
                        System.out.println("Veuillez entrer une touche :");
                    else
                        System.out.println(choix);
                    break;
            }
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame state, Graphics g) throws SlickException {
        try {
            int j = 0;
            playersOptionsTTF.drawString(100, 100, Objects.requireNonNull(getLanguageJson()).getJSONObject(getLanguage()).getString("Keyboard Mapping :"), notChosen);

            for (int i = 0; i < action.size(); i++) {
                if (playersChoice == i) playersOptionsTTF.drawString(100, i * 50 + 175, getLanguageJson().getJSONObject(getLanguage()).getString(action.get(i)));
                else playersOptionsTTF.drawString(100, i * 50 + 175, getLanguageJson().getJSONObject(getLanguage()).getString(action.get(i)), notChosen);

                playersOptionsTTF.drawString(400, i * 50 + 175, ": " + nameKeyboard.get(i), notChosen);
            }

            for (int i = 0; i < playersOptions.size(); i++) {
                if (!action.contains(playersOptions.get(i))) {
                    if (playersChoice == i)
                        playersOptionsTTF.drawString(100, j * 50 + 200 + 50 * action.size(), getLanguageJson().getJSONObject(getLanguage()).getString(playersOptions.get(i)));
                    else
                        playersOptionsTTF.drawString(100, j * 50 + 200 + 50 * action.size(), getLanguageJson().getJSONObject(getLanguage()).getString(playersOptions.get(i)), notChosen);

                    j++;
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
