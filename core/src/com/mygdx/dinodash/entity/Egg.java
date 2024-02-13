package com.mygdx.dinodash.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.dinodash.MyGame;

public class Egg extends B2DSprite {
    public Egg(Body body){
        super(body);
        Texture texture = MyGame.resources.getTextture("egg");
        TextureRegion[] sprites = TextureRegion.split(texture,16,16)[0];
        animation.setFrames(sprites, 1 / 30f);

        width = sprites[0].getRegionWidth();
        height = sprites[0].getRegionHeight();
    }
}
