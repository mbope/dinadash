package com.mygdx.dinodash.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.dinodash.handlers.Animation;
import com.mygdx.dinodash.handlers.B2DVars;

public class B2DSprite {
    protected Body body;
    protected Animation animation;
    protected float width;
    protected float height;

    public B2DSprite(Body body){
        this.body = body;
        animation = new Animation();
    }

    public void setAnimation(TextureRegion region, float delay) {
        setAnimation(new TextureRegion[] { region }, delay);
    }

    public void setAnimation(TextureRegion[] regions, float delay){
        animation.setFrames(regions,delay);
        width = regions[0].getRegionWidth();
        height = regions[0].getRegionHeight();
    }

    public void update(float delta){
        animation.update(delta);
    }

    public void render(SpriteBatch sb){
        sb.begin();
        sb.draw(animation.getFrame(), (body.getPosition().x * B2DVars.PPM - width / 2), (int) (body.getPosition().y * B2DVars.PPM - height / 2));
        sb.end();
    }

    public Body getBody(){
        return body;
    }

    public Vector2 getPosition(){
        return body.getPosition();
    }

    public float getWidth(){
        return width;
    }

    public float getHeight() {
        return height;
    }
}
