package com.knadr.menu;

import com.knadr.util.Mode;
import org.json.JSONException;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Font;
import java.util.Objects;

import static com.knadr.menu.MainMenu.getLanguage;
import static com.knadr.menu.MainMenu.getLanguageJson;
import static com.knadr.menu.MainMenu.pseudo;

public class PseudoMenu extends BasicGameState {

    public static final int ID = 8;

    private GameContainer container;
    private TrueTypeFont playersOptionsTTF;
    private int tailleMaxPseudo = 30;

    @Override
    public int getID() { return ID; }

    @Override
    public void init(GameContainer gc, StateBasedGame state) throws SlickException {
        this.container = gc;
        Font font = new Font("Verdana", Font.BOLD, 40);
        playersOptionsTTF = new TrueTypeFont(font, true);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame state, int delta) throws SlickException {
        Input input = gc.getInput();

        if (input.isKeyPressed(Input.KEY_ENTER)) {
            if (pseudo.length() > 0) {
                if (pseudo.charAt(pseudo.length() - 1) == '_')
                    pseudo = new StringBuilder(pseudo).deleteCharAt(pseudo.length() - 1).toString();

                if (pseudo.length() > 0) {
                    if (MainMenu.mode.equals(Mode.CLASSIC))
                        state.enterState(ClassicLoader.ID);

                    if (MainMenu.mode.equals(Mode.ADVENTURE))
                        state.enterState(AdventureLoader.ID);
                }
                else {
                    /**
                     * afficher un message pour dire que le pseudo saisi est vide
                     */
                }
            }
        }

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            gc.reinit();
            state.enterState(MainMenu.ID);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame state, Graphics graphics) throws SlickException {
        try {
            playersOptionsTTF.drawString(100, 100, Objects.requireNonNull(getLanguageJson()).getJSONObject(getLanguage()).getString("Enter your nickname :"), new Color(153, 204, 255));
            playersOptionsTTF.drawString(100, 175, pseudo, new Color(153, 204, 255));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        saisiePseudo(key);
    }

    private void saisiePseudo(int key) {
        if (pseudo.length() == 0)
            pseudo = "_";

        if (pseudo.charAt(pseudo.length() - 1) == '_')
            pseudo = new StringBuilder(pseudo).deleteCharAt(pseudo.length() - 1).toString();

        if (key != Input.KEY_ENTER) {
            if (pseudo.length() + 1 < tailleMaxPseudo) {
                switch (key) {
                    case Input.KEY_BACK:
                        if (pseudo.length() > 0)
                            pseudo = new StringBuilder(pseudo).deleteCharAt(pseudo.length() - 1).toString();
                        break;
                    case Input.KEY_SPACE:
                        pseudo += " ";
                        break;
                    case Input.KEY_A:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "A";
                        else
                            pseudo += "a";
                        break;
                    case Input.KEY_B:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "B";
                        else
                            pseudo += "b";
                        break;
                    case Input.KEY_C:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "C";
                        else
                            pseudo += "c";
                        break;
                    case Input.KEY_D:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "D";
                        else
                            pseudo += "d";
                        break;
                    case Input.KEY_E:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "E";
                        else
                            pseudo += "e";
                        break;
                    case Input.KEY_F:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "F";
                        else
                            pseudo += "f";
                        break;
                    case Input.KEY_G:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "G";
                        else
                            pseudo += "g";
                        break;
                    case Input.KEY_H:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "H";
                        else
                            pseudo += "h";
                        break;
                    case Input.KEY_I:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "I";
                        else
                            pseudo += "i";
                        break;
                    case Input.KEY_J:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "J";
                        else
                            pseudo += "j";
                        break;
                    case Input.KEY_K:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "K";
                        else
                            pseudo += "k";
                        break;
                    case Input.KEY_L:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "L";
                        else
                            pseudo += "l";
                        break;
                    case Input.KEY_M:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "M";
                        else
                            pseudo += "m";
                        break;
                    case Input.KEY_N:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "N";
                        else
                            pseudo += "n";
                        break;
                    case Input.KEY_O:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "O";
                        else
                            pseudo += "o";
                        break;
                    case Input.KEY_P:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "P";
                        else
                            pseudo += "p";
                        break;
                    case Input.KEY_Q:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "Q";
                        else
                            pseudo += "q";
                        break;
                    case Input.KEY_R:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "R";
                        else
                            pseudo += "r";
                        break;
                    case Input.KEY_S:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "S";
                        else
                            pseudo += "s";
                        break;
                    case Input.KEY_T:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "T";
                        else
                            pseudo += "t";
                        break;
                    case Input.KEY_U:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "U";
                        else
                            pseudo += "u";
                        break;
                    case Input.KEY_V:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "V";
                        else
                            pseudo += "v";
                        break;
                    case Input.KEY_W:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "W";
                        else
                            pseudo += "w";
                        break;
                    case Input.KEY_X:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "X";
                        else
                            pseudo += "x";
                        break;
                    case Input.KEY_Y:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "Y";
                        else
                            pseudo += "y";
                        break;
                    case Input.KEY_Z:
                        if (this.container.getInput().isKeyPressed(Input.KEY_LSHIFT) || this.container.getInput().isKeyPressed(Input.KEY_RSHIFT))
                            pseudo += "Z";
                        else
                            pseudo += "z";
                        break;
                    case Input.KEY_1:
                        pseudo += "1";
                        break;
                    case Input.KEY_2:
                        pseudo += "2";
                        break;
                    case Input.KEY_3:
                        pseudo += "4";
                        break;
                    case Input.KEY_5:
                        pseudo += "5";
                        break;
                    case Input.KEY_6:
                        pseudo += "6";
                        break;
                    case Input.KEY_7:
                        pseudo += "7";
                        break;
                    case Input.KEY_8:
                        pseudo += "8";
                        break;
                    case Input.KEY_9:
                        pseudo += "9";
                        break;
                    case Input.KEY_0:
                        pseudo += "0";
                        break;
                }

                pseudo += "_";
            }
            else {
                if (key == Input.KEY_BACK)
                    pseudo = new StringBuilder(pseudo).deleteCharAt(pseudo.length() - 1).toString();
            }
        }
    }
}
