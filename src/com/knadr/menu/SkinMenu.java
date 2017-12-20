package com.knadr.menu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

import static com.knadr.menu.MainMenu.getLanguage;
import static com.knadr.menu.MainMenu.getLanguageJson;
import static com.knadr.util.Find.findKeyArray;
import static com.knadr.util.Find.findPositionSkins;
import static com.knadr.util.JSON.chercheKeyJSONArray;
import static com.knadr.util.JSON.chercheSkin;

public class SkinMenu extends BasicGameState {

    public static final int ID = 7;

    private GameContainer container;
    private int playersChoice = 0;
    private ArrayList<String> playersOptions = new ArrayList<>();
    private static int NOCHOICES;
    private TrueTypeFont playersOptionsTTF;
    private Color notChosen = new Color(153, 204, 255);
    private ArrayList<String> skinCanadair = new ArrayList<>();
    private ArrayList<String> skinMap = new ArrayList<>();
    private JSONArray settingsJson = Objects.requireNonNull(MainMenu.getMenuJson()).getJSONObject(chercheKeyJSONArray(Objects.requireNonNull(MainMenu.getMenuJson()),"settings")).getJSONArray("settings");
    private String adresseSettings;
    private JSONObject settingJson;
    private JSONArray skinJson;
    private JSONObject skinsJson;
    private Image box;
    private Image check;

    public SkinMenu() throws JSONException { }

    @Override
    public int getID() { return ID; }

    @Override
    public void init(GameContainer gc, StateBasedGame state) throws SlickException {
        try {
            adresseSettings = new String(Files.readAllBytes(Paths.get("res" + File.separator + "fileGame" + File.separator + "settings.json")));
            settingJson = new JSONObject(adresseSettings);
            skinsJson = settingJson.getJSONObject("skin");
            this.container = gc;
            box = new Image("res" + File.separator + "img" + File.separator  + "menu" + File.separator + "box.png");
            check = new Image("res" + File.separator + "img" + File.separator + "menu" + File.separator + "check.png");
            java.awt.Font font = new java.awt.Font("Verdana", java.awt.Font.BOLD, 40);
            playersOptionsTTF = new TrueTypeFont(font, true);

            if (findKeyArray(settingsJson, "skin") != -1)
                skinJson = settingsJson.getJSONObject(findKeyArray(settingsJson, "skin")).getJSONArray("skin");

            for (int i=0; i < skinsJson.getJSONArray("canadair").length(); i++) {
                skinCanadair.add(skinsJson.getJSONArray("canadair").getJSONObject(i).getString("name"));
                playersOptions.add("canadair : " + skinsJson.getJSONArray("canadair").getJSONObject(i).getString("name"));
            }

            for (int i=0; i < skinsJson.getJSONArray("map").length(); i++) {
                skinMap.add(skinsJson.getJSONArray("map").getJSONObject(i).getString("name"));
                playersOptions.add("map : " + skinsJson.getJSONArray("map").getJSONObject(i).getString("name"));
            }

            for (int i=0; i < skinJson.length(); i++)
                if (skinJson.get(i).getClass().getSimpleName().equals("JSONObject"))
                    playersOptions.add(skinJson.getJSONObject(i).names().getString(0));
                else if (skinJson.get(i).getClass().getSimpleName().equals("String"))
                    playersOptions.add(skinJson.getString(i));

            NOCHOICES = playersOptions.size();

        }
        catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame state, int delta) throws SlickException {
        try {
            Input input = gc.getInput();

            if (input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_BACK))
                state.enterState(SettingsMenu.ID);


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
                        if (choix.startsWith("canadair :") || choix.startsWith("map :")) {
                            FileWriter file = new FileWriter(System.getProperty("user.dir") + File.separator + "res" + File.separator + "fileGame" + File.separator + "settings.json");
                            String skinType = choix.split(" : ")[0];
                            String skinName = choix.split(" : ")[1];

                            for (int i=0; i < settingJson.getJSONObject("skin").getJSONArray(skinType).length(); i++) {
                                if (i == chercheSkin(settingJson.getJSONObject("skin").getJSONArray(skinType), skinName))
                                    settingJson.getJSONObject("skin").getJSONArray(skinType).getJSONObject(i).put("selected", true);
                                else
                                    settingJson.getJSONObject("skin").getJSONArray(skinType).getJSONObject(i).put("selected", false);
                            }

                            file.write(settingJson.toString());
                            file.flush();
                            file.close();
                        }
                        else
                            System.out.println(choix);
                        break;
                }
            }
        }
        catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame state, Graphics g) throws SlickException {
        try {
            int j = 0;
            playersOptionsTTF.drawString(100, 100, Objects.requireNonNull(getLanguageJson()).getJSONObject(getLanguage()).getString("Skins :"), notChosen);
            playersOptionsTTF.drawString(100, 150, "     Canadair :", notChosen);

            for (int i = 0; i < skinCanadair.size(); i++) {
                if (playersChoice == i) playersOptionsTTF.drawString(100, i * 50 + 200, "          " + getLanguageJson().getJSONObject(getLanguage()).getString(skinCanadair.get(i)));
                else playersOptionsTTF.drawString(100, i * 50 + 200, "          " + getLanguageJson().getJSONObject(getLanguage()).getString(skinCanadair.get(i)), notChosen);

                box.draw(500, i * 50 + 210);

                if (skinsJson.getJSONArray("canadair").getJSONObject(i).getBoolean("selected"))
                    check.draw(500, i * 50 + 210);
            }

            playersOptionsTTF.drawString(100, 200 + 50 * skinCanadair.size(), "     Map :", notChosen);

            for (int i = 0; i < skinMap.size(); i++) {
                if (playersChoice > findPositionSkins(playersOptions, "canadair :") && playersChoice <= findPositionSkins(playersOptions, "map :"))
                    playersOptionsTTF.drawString(100, i * 50 + 250 + 50 * skinCanadair.size(), "          " + getLanguageJson().getJSONObject(getLanguage()).getString(skinMap.get(i)));
                else
                    playersOptionsTTF.drawString(100, i * 50 + 250 + 50 * skinCanadair.size(), "          " + getLanguageJson().getJSONObject(getLanguage()).getString(skinMap.get(i)), notChosen);

                box.draw(500, i * 50 + 260 + 50 * skinCanadair.size());

                if (skinsJson.getJSONArray("map").getJSONObject(i).getBoolean("selected"))
                    check.draw(500, i * 50 + 260 + 50 * skinCanadair.size());
            }

            for (int i = 0; i < playersOptions.size(); i++) {
                if (!playersOptions.get(i).startsWith("canadair :") && !playersOptions.get(i).startsWith("map :")) {
                    if (playersChoice == i)
                        playersOptionsTTF.drawString(100, j * 50 + 250 + 50 * skinCanadair.size() + 50 * skinMap.size(), getLanguageJson().getJSONObject(getLanguage()).getString(playersOptions.get(i)));
                    else
                        playersOptionsTTF.drawString(100, j * 50 + 250 + 50 * skinCanadair.size() + 50 * skinMap.size(), getLanguageJson().getJSONObject(getLanguage()).getString(playersOptions.get(i)), notChosen);

                    j++;
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
