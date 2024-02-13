package com.mygdx.dinodash.handlers;

import com.mygdx.dinodash.MyGame;
import com.mygdx.dinodash.states.GameState;

import java.util.Stack;

public class GameStateManager {
    private MyGame game;
    private Stack<GameState> gameStates;

    public static final int MENU = 83774392;
    public static final int PLAY = 388031654;
    public static final int LEVEL_SELECT = -9238732;

    public GameStateManager(MyGame game){
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(MENU);
    }

    public void setState(int state){
        popState();
        pushState(state);
    }

    public void pushState(int state){
        gameStates.push(getState(state));
    }

    public void popState(){
        GameState g = gameStates.pop();
        g.dispose();
    }

    private GameState getState(int state){

    }
}
