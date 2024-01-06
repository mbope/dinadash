package com.mygdx.dinodash.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.dinodash.entity.BaseActor;
import com.mygdx.dinodash.entity.DinoActor;

public class GameScreen extends BaseScreen{
    private DinoActor dino;
    private BaseActor obstacle;
    private float timeElapsed;
    private Label timeLabel;

    public GameScreen(Game game){
        super(game);
    }
    @Override
    public void create() {
        timeElapsed = 0;
        obstacle = new BaseActor();
        obstacle.setTextture(new Texture(Gdx.files.internal("")));
    }

    @Override
    public void update(float delta) {

    }
}
