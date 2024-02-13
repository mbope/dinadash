package com.mygdx.dinodash.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.dinodash.MyGame;

public class Player extends B2DSprite {
    private int numEggs;
    private int totalEggs;

    public Player(Body body){
        super(body);
        Texture texture = MyGame.resources.getTextture("dino");
        TextureRegion[] sprites = new TextureRegion[4];
        for(int i=0; i < sprites.length; i++){
            sprites[i] = new TextureRegion(texture,i*32,0,32,32);
        }
        animation.setFrames(sprites, 1 / 12f);

        width = sprites[0].getRegionWidth();
        height = sprites[0].getRegionHeight();
    }

    public void collectEgg(){
        numEggs++;
    }

    public int getNumEggs(){
        return numEggs;
    }

    public void setTotalEggs(int i){
        totalEggs = i;
    }

    public int getTotalEggs(){
        return totalEggs;
    }
}
