package com.knadr.menu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.newdawn.slick.Color;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ScoreAdventure extends BasicGameState {

    public static final int ID = 5;
    private int playersChoice = 0;
    private ArrayList<String> playersOptions = new ArrayList<>();
    private ArrayList<Integer> bestScores = new ArrayList<>();
    private ArrayList<String> pseudo = new ArrayList<>();
    private static int NOCHOICES;
    private boolean exit = false;
    private TrueTypeFont playersOptionsTTF;
    private Color notChosen = new Color(153, 204, 255);
    private String adresseScore = new String(Files.readAllBytes(Paths.get("res" + File.separator + "fileGame" + File.separator + "score.json")));
    private JSONArray scoresJson = new JSONObject(adresseScore).getJSONObject("Best Score").getJSONObject("adventure").getJSONArray("list");
    private int length = new JSONObject(adresseScore).getJSONObject("Best Score").getJSONObject("adventure").getInt("nb");

    public ScoreAdventure() throws JSONException, IOException { }

    @Override
    public int getID() { return ID; }

    @Override
    public void init(GameContainer gc, StateBasedGame stateBasedGame) throws SlickException {
        try {
            Font font = new Font("Verdana", Font.BOLD, 40);
            playersOptionsTTF = new TrueTypeFont(font, true);
            font = new Font ("Verdana", Font.PLAIN, 20);
            TrueTypeFont foo = new TrueTypeFont(font, true);

            playersOptions.add("return");

            for (int i=0; i < length; i++) {
                bestScores.add(scoresJson.getJSONObject(i).getInt("score"));
                pseudo.add(scoresJson.getJSONObject(i).getString("pseudo"));
            }

            NOCHOICES = playersOptions.size();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(GameContainer gc, StateBasedGame stateBasedGame, int delta) throws SlickException {
        //int xpos = Mouse.getX();
        //int ypos = Mouse.getY();
        //String mouse = "x: " + xpos + " y: " + ypos;
        //System.out.println(mouse);
        //System.out.println(Mouse.isButtonDown(0));

        Input input = gc.getInput();

        if (input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_BACK)) stateBasedGame.enterState(ScoreMenu.ID);

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
                    stateBasedGame.enterState(ScoreMenu.ID);
                    break;
                default:
                    System.out.println(choix);
                    break;
            }
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        renderPlayersOptions();

        if (exit) gc.exit();
    }

    private void renderPlayersOptions() {
        playersOptionsTTF.drawString(100, 30, "Adventure : best score");

        for (int i = 0; i < playersOptions.size(); i++)
            if (playersChoice == i) playersOptionsTTF.drawString(100, i * 50 + 650, playersOptions.get(i));
            else playersOptionsTTF.drawString(100, i * 50 + 650, playersOptions.get(i), notChosen);

        for (int i = 0; i < length; i++) {
            playersOptionsTTF.drawString(100, i * 50 + 100, pseudo.get(i));
            playersOptionsTTF.drawString(500, i * 50 + 100, String.valueOf(bestScores.get(i)));
        }
    }
}
