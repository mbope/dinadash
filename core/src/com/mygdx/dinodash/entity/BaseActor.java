package com.mygdx.dinodash.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Logger;

public class BaseActor extends Actor {
    // Logger
    private static final Logger log = new Logger(BaseActor.class.getName());

    private final Circle collisionShape = new Circle();
    public TextureRegion region;
    public Rectangle boundary;
    public float velocityX;
    public float velocityY;

    public BaseActor(){
        region = new TextureRegion();
        boundary = new Rectangle();
        velocityX = 0;
        velocityY = 0;
    }

    public void setCollisionRadius(float radius){
        collisionShape.setRadius(radius);
    }

    public void setTextture(Texture t){
        // validate parameter
        if(t == null){
            throw new IllegalArgumentException("texture parameter is required");
        }
        int w = t.getWidth();
        int h = t.getHeight();
        setWidth(w);
        setHeight(h);
        region.setRegion(t);
    }

    public Rectangle getBoundingRectangle(){
        boundary.set(getX(),getY(),getWidth(),getHeight());
        return boundary;
    }

    @Override
    public void act(float delta){
        super.act(delta);
        moveBy(velocityX * delta, velocityY * delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(region == null){
            log.error("Region is not set on Actor "+getClass().getName());
        }
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);
        if(isVisible()){
            batch.draw(region, getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
        }
    }

    @Override
    public void drawDebugBounds(ShapeRenderer shapes) {
        if(!getDebug()){
            return;
        }
        Color oldColor =  shapes.getColor().cpy();

        shapes.setColor(Color.RED);
        shapes.x(collisionShape.x, collisionShape.y,0.1f);
        shapes.circle(collisionShape.x,collisionShape.y,collisionShape.radius,30);

        shapes.setColor(oldColor);
    }
}
