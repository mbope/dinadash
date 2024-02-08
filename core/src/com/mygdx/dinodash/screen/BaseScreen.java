package com.mygdx.dinodash.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.dinodash.config.GameConfig;
import com.mygdx.dinodash.util.GdxUtils;

public abstract class BaseScreen implements Screen, InputProcessor {
    protected Game game;
    protected AssetManager assetManager;
    protected Stage mainStage;
    protected Stage uiStage;

    public static final float viewWidth = GameConfig.WIDTH;
    public static final float viewHeight = GameConfig.HEIGHT;
    private boolean paused;

    public BaseScreen(Game game){
        this.game = game;
        assetManager = new AssetManager();
        mainStage = new Stage(new FitViewport(viewWidth, viewHeight));
        uiStage = new Stage(new FitViewport(viewWidth, viewHeight));

        paused = false;

        InputMultiplexer inputMultiplexer = new InputMultiplexer(this, uiStage, mainStage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        create();
    }

    public abstract void create();
    public abstract void update(float delta);

    @Override
    public void render(float delta) {
        uiStage.act(delta);
        if(!paused){
            mainStage.act(delta);
            update(delta);
        }
        GdxUtils.clearScreen();
        mainStage.draw();
        uiStage.draw();
    }

    public boolean isPaused(){
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void togglePaused(){
        paused = !paused;
    }

    @Override
    public void resize(int width, int height) {
        mainStage.getViewport().update(width, height, true);
        uiStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause(){}
    @Override
    public void resume(){}
    @Override
    public void dispose(){}
    @Override
    public void show(){}
    @Override
    public void hide(){}

    @Override
    public boolean keyDown(int keyCode){
        return false;
    }

    @Override
    public boolean keyTyped(char c){
        return false;
    }

    @Override
    public boolean keyUp(int keyCode){
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }
}
