package com.mygdx.dinodash.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.dinodash.config.GameConfig;

public class DinoActor extends BaseActor{
    public Animation animation;
    public float elapsedTime;

    public DinoActor(){
        super();
        setCollisionRadius(GameConfig.PLAYER_BOUNDS_RADIUS);
        setSize(GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE);
        animation = null;
        elapsedTime = 0;
    }

    public void setAnimation(Animation a){
        if(a == null){
            return;
        }
        TextureRegion t = (TextureRegion) a.getKeyFrame(0);
        setTextture(t.getTexture());
        animation = a;
    }

    public void act(float delta){
        super.act(delta);
        elapsedTime += delta;
        update();
    }

    public void update(){
        float xSpeed = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            xSpeed = GameConfig.PLAYER_SPEED;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xSpeed = -GameConfig.PLAYER_SPEED;
        }
        setX(getX()+xSpeed);
        blockPlayerFromLeavingTheWorld();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(animation != null){
            TextureRegion t = (TextureRegion) animation.getKeyFrame(elapsedTime);
            region.setRegion(t.getTexture());
            super.draw(batch, parentAlpha);
        }

    }

    private void blockPlayerFromLeavingTheWorld(){
        float playerX = MathUtils.clamp(getX(),0, GameConfig.WORLD_WIDTH -getWidth());
        setPosition(playerX,getY());
    }
}
