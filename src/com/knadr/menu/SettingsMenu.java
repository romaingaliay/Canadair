package com.knadr.menu;

import com.knadr.util.Language;
import com.knadr.util.Resolution;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

import static com.knadr.menu.MainMenu.getLanguage;
import static com.knadr.menu.MainMenu.getLanguageJson;
import static com.knadr.util.Find.findIndexLang;
import static com.knadr.util.Find.findIndexRes;
import static com.knadr.util.JSON.chercheKeyJSONArray;

public class SettingsMenu extends BasicGameState {

    public static final int ID = 3;
    private int playersChoice = 0;
    private ArrayList<String> playersOptions = new ArrayList<>();
    private static int NOCHOICES;
    private TrueTypeFont playersOptionsTTF;
    private Color notChosen = new Color(153, 204, 255);
    private JSONArray settingsJson = Objects.requireNonNull(MainMenu.getMenuJson()).getJSONObject(chercheKeyJSONArray(Objects.requireNonNull(MainMenu.getMenuJson()),"settings")).getJSONArray("settings");
    private JSONObject settingJson;
    private ArrayList<Resolution> resolutions = new ArrayList<>();
    private ArrayList<Language> languages = new ArrayList<>();
    private int indexRes = -1;
    private int indexLang = -1;
    private Image box;
    private Image check;

    public SettingsMenu() throws JSONException {}

    @Override
    public int getID() { return ID; }

    @Override
    public void init(GameContainer gc, StateBasedGame state) throws SlickException {
        try {
            String adresseSetting = new String(Files.readAllBytes(Paths.get("res" + File.separator + "fileGame" + File.separator + "settings.json")));
            settingJson = new JSONObject(adresseSetting);
            resolutions.add(Resolution.HD);
            resolutions.add(Resolution.AHD);
            resolutions.add(Resolution.FULLHD);
            resolutions.add(Resolution.WQHD);
            languages.add(Language.EN);
            languages.add(Language.FR);
            box = new Image("res" + File.separator + "img" + File.separator  + "menu" + File.separator + "box.png");
            check = new Image("res" + File.separator + "img" + File.separator + "menu" + File.separator + "check.png");
            Font font = new Font("Verdana", Font.BOLD, 40);
            playersOptionsTTF = new TrueTypeFont(font, true);
            font = new Font ("Verdana", Font.PLAIN, 20);
            TrueTypeFont foo = new TrueTypeFont(font, true);

            for (int i=0; i < settingsJson.length(); i++)
                if (settingsJson.get(i).getClass().getSimpleName().equals("JSONObject"))
                    playersOptions.add(settingsJson.getJSONObject(i).names().getString(0));
                else if (settingsJson.get(i).getClass().getSimpleName().equals("String"))
                    playersOptions.add(settingsJson.getString(i));

            NOCHOICES = playersOptions.size();
        }
        catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(GameContainer gc, StateBasedGame state, int delta) throws SlickException {
        try {
            //int xpos = Mouse.getX();
            //int ypos = Mouse.getY();
            //String mouse = "x: " + xpos + " y: " + ypos;
            //System.out.println(mouse);
            //System.out.println(Mouse.isButtonDown(0));

            Input input = gc.getInput();

            if (input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_BACK)) {
                gc.reinit();
                state.enterState(MainMenu.ID);
            }

            if (input.isKeyPressed(Input.KEY_DOWN))
                if (playersChoice == (NOCHOICES - 1)) playersChoice = 0;
                else playersChoice++;

            if (input.isKeyPressed(Input.KEY_UP))
                if (playersChoice == 0) playersChoice = NOCHOICES - 1;
                else playersChoice--;

            if (input.isKeyPressed(Input.KEY_ENTER)) {
                String choix = playersOptions.get(playersChoice);
                FileWriter file = new FileWriter(System.getProperty("user.dir") + File.separator + "res" + File.separator + "fileGame" + File.separator + "settings.json");

                switch (choix) {
                    case "keyboard mapping":
                        state.enterState(KeyBoardMenu.ID);
                        break;
                    case "skin":
                        state.enterState(SkinMenu.ID);
                        break;
                    case "return":
                        state.enterState(MainMenu.ID);
                        break;
                    case "full screen":
                    case "v sync":
                    case "show fps":
                        settingJson.getJSONObject("settings").put(choix, !settingJson.getJSONObject("settings").getBoolean(choix));
                        break;
                    case "language":
                        if (indexLang == (languages.size() - 1))
                            settingJson.getJSONObject("settings").put(choix, languages.get(0).toString());
                        else
                        settingJson.getJSONObject("settings").put(choix, languages.get(indexLang + 1).toString());
                        break;
                    case "resolution":
                        if (indexRes == resolutions.size() - 1)
                            settingJson.getJSONObject("settings").put(choix, resolutions.get(0).toString());
                        else
                            settingJson.getJSONObject("settings").put(choix, resolutions.get(indexRes + 1).toString());
                        break;
                    default:
                        System.out.println(choix);
                        break;
                }

                file.write(settingJson.toString());
                file.flush();
                file.close();
            }
        }
        catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame state, Graphics g) throws SlickException {
        try {
            indexLang = findIndexLang(languages, settingJson.getJSONObject("settings").getString("language"));
            indexRes = findIndexRes(resolutions, settingJson.getJSONObject("settings").getString("resolution"));


            for (int i = 0; i < playersOptions.size(); i++) {
                if (playersChoice == i) playersOptionsTTF.drawString(100, i * 50 + 200, Objects.requireNonNull(getLanguageJson()).getJSONObject(getLanguage()).getString(playersOptions.get(i)));
                else playersOptionsTTF.drawString(100, i * 50 + 200, Objects.requireNonNull(getLanguageJson()).getJSONObject(getLanguage()).getString(playersOptions.get(i)), notChosen);

                if (settingJson.getJSONObject("settings").names().toString().contains(playersOptions.get(i))) {
                    if (settingJson.getJSONObject("settings").get(playersOptions.get(i)).getClass().getSimpleName().equals("Boolean")) {
                        box.draw(500, i * 50 + 200);

                        if (settingJson.getJSONObject("settings").getBoolean(playersOptions.get(i)))
                            check.draw(500, i * 50 + 200);
                    }

                    if (settingJson.getJSONObject("settings").get(playersOptions.get(i)).getClass().getSimpleName().equals("String")) {
                        playersOptionsTTF.drawString(500, i * 50 + 200, settingJson.getJSONObject("settings").getString(playersOptions.get(i)), notChosen);
                    }
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
