package com.mygdx.dinodash;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.dinodash.handlers.BoundedCamera;
import com.mygdx.dinodash.handlers.Content;
import com.mygdx.dinodash.handlers.DDInputProcessor;
import com.mygdx.dinodash.handlers.GameStateManager;

public class MyGame implements ApplicationListener {
    public static final String TITLE = "Dino Dash";
    public static final int V_WIDTH = 320;
    public static final int V_HEIGHT = 240;
    public static final int SCALE = 2;
    public static final float STEP = 1/60f;

    private SpriteBatch sb;
    private BoundedCamera cam;
    private OrthographicCamera hudCam;

    private GameStateManager gsm;

    public static Content resources;
    @Override
    public void create() {
        Gdx.input.setInputProcessor(new DDInputProcessor());

        resources = new Content();
        resources.loadTexture("imgs/dino.jpeg");
        resources.loadTexture("imgs/startbg.png");
        resources.loadTexture("imgs/the_world.png");

        cam = new BoundedCamera();
        cam.setToOrtho(false);
        hudCam = new OrthographicCamera();
        hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);
        sb = new SpriteBatch();
        gsm = new GameStateManager(this);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.graphics.setTitle(TITLE + " -- FPS: " + Gdx.graphics.getFramesPerSecond());
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
