package com.mygdx.dinodash.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.dinodash.entity.BaseActor;
import com.mygdx.dinodash.entity.DinoActor;
import com.mygdx.dinodash.util.GdxUtils;

import java.time.Instant;

public class GameScreen extends BaseScreen{
    private static final Logger log = new Logger(GameScreen.class.getName());
    private DinoActor dino;
    private BaseActor obstacle;
    private float timeElapsed;
    private Label timeLabel;

    private OrthographicCamera camera;

    private final int startX = 1100;
    private final int startY = 1225;
    private int endX = 2350;
    private int endY = 600;

    private float minAltitude = 0.5f;
    private float maxAltitude = 2.5f;

    private float percent;
    private float counter;
    private long startTime;
    private final float animation_duration = 15000;

    public GameScreen(Game game){
        super(game);
        Image map = new Image(new Texture("imgs/startbg.png"));
        uiStage.addActor(map);
        camera = (OrthographicCamera) uiStage.getViewport().getCamera();
        camera.translate(startX, startY);
        counter = 0;
        startTime = System.currentTimeMillis();
    }
    @Override
    public void create() {
        timeElapsed = 0;
        obstacle = new BaseActor();
        //obstacle.setTextture(new Texture(Gdx.files.internal("")));
        long secondFromStart = System.currentTimeMillis() - startTime;
        percent = (secondFromStart%animation_duration/animation_duration);
        percent = (float) Math.cos(percent*Math.PI)/ 2+0.5f;
        log.debug("Render()");
        GdxUtils.clearScreen();
        moveCamera();
    }

    @Override
    public void update(float delta) {

    }

    private void moveCamera(){
        log.debug("try to move camera");
        float currentX = startX + (endX - startX) * percent;
        float currentY = startY + (endY - startY)*percent;
        float percentZ = Math.abs(percent - 0.5f)*2;
        float currentZ = maxAltitude - (maxAltitude - minAltitude)*percentZ;
        log.debug("move data--> currentX: "+currentX+", currentY: "+currentY+", currentZ: "+currentZ);

        /*camera.position.x = currentX;
        camera.position.y = currentY;
        camera.zoom = currentZ;
        camera .update();*/
    }
}
