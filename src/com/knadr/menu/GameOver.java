package com.knadr.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOver extends BasicGameState {

    public static final int ID = 11;

    private GameContainer container;

    @Override
    public int getID() { return ID; }

    @Override
    public void init(GameContainer gc, StateBasedGame state) throws SlickException {
        this.container = gc;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame state, Graphics graphics) throws SlickException {

    }

    @Override
    public void update(GameContainer gc, StateBasedGame state, int delta) throws SlickException {

        Input input = gc.getInput();

        if (input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_BACK)) {
            gc.reinit();
            state.enterState(MainMenu.ID);
        }
    }
}
