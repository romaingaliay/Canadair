package menu;

import org.json.JSONArray;
import org.json.JSONException;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import static menu.MainMenu.getMenuJson;
import static util.JSON.chercheKeyJSONArray;

public class SettingMenu extends BasicGameState {

    private final int ID = 3;
    private int playersChoice = 0;
    private ArrayList<String> playersOptions = new ArrayList<>();
    private static int NOCHOICES;
    private boolean exit = false;
    private TrueTypeFont playersOptionsTTF;
    private Color notChosen = new Color(153, 204, 255);
    private JSONArray scoreJson = getMenuJson().getJSONObject(chercheKeyJSONArray(getMenuJson(),"settings")).getJSONArray("settings");

    public SettingMenu() throws JSONException, IOException { }

    @Override
    public int getID() { return ID; }

    @Override
    public void init(GameContainer gc, StateBasedGame stateBasedGame) throws SlickException {
        try {
            Font font = new Font("Verdana", Font.BOLD, 40);
            playersOptionsTTF = new TrueTypeFont(font, true);
            font = new Font ("Verdana", Font.PLAIN, 20);
            TrueTypeFont foo = new TrueTypeFont(font, true);

            for (int i=0; i < scoreJson.length(); i++)
                if (scoreJson.get(i).getClass().getSimpleName().equals("JSONObject"))
                    playersOptions.add(scoreJson.getJSONObject(i).names().getString(0));
                else if (scoreJson.get(i).getClass().getSimpleName().equals("String"))
                    playersOptions.add(scoreJson.getString(i));

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

        if (input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_BACK)) stateBasedGame.enterState(1);

        if (input.isKeyPressed(Input.KEY_DOWN))
            if (playersChoice == (NOCHOICES - 1)) playersChoice = 0;
            else playersChoice++;

        if (input.isKeyPressed(Input.KEY_UP))
            if (playersChoice == 0) playersChoice = NOCHOICES - 1;
            else playersChoice--;

        if (input.isKeyPressed(Input.KEY_ENTER)) {
            switch (playersOptions.get(playersChoice)) {
                case "return":
                    stateBasedGame.enterState(1);
                    break;
                default:

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
        for (int i = 0; i < playersOptions.size(); i++)
            if (playersChoice == i) playersOptionsTTF.drawString(100, i * 50 + 200, playersOptions.get(i));
            else playersOptionsTTF.drawString(100, i * 50 + 200, playersOptions.get(i), notChosen);
    }
}
