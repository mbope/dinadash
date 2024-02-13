package com.mygdx.dinodash.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.dinodash.MyGame;
import com.mygdx.dinodash.handlers.BoundedCamera;
import com.mygdx.dinodash.handlers.GameStateManager;

public abstract class GameState {
    protected GameStateManager gsm;
    protected MyGame game;

    protected SpriteBatch sb;
    protected BoundedCamera cam;
    protected OrthographicCamera hudCam;

    protected GameState(GameStateManager gsm){
        this.gsm = gsm;
    }
}
