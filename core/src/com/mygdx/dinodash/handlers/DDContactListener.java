package com.mygdx.dinodash.handlers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;

public class DDContactListener implements ContactListener {
    private int numFootContacts;
    private Array<Body> obstacles;
    private boolean playerDead;

    public DDContactListener(){
        super();
        obstacles = new Array<Body>();
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if(fa == null || fb == null){
            return;
        }

        if(fa.getUserData() != null && fa.getUserData().equals("foot")){
            numFootContacts++;
        }
        if(fb.getUserData() != null && fb.getUserData().equals("foot")){
            numFootContacts++;
        }
        if(fa.getUserData() != null && fa.getUserData().equals("crystal")) {
            obstacles.add(fa.getBody());
        }
        if(fb.getUserData() != null && fb.getUserData().equals("crystal")) {
            obstacles.add(fb.getBody());
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if(fa == null || fb == null) return;

        if(fa.getUserData() != null && fa.getUserData().equals("foot")) {
            numFootContacts--;
        }
        if(fb.getUserData() != null && fb.getUserData().equals("foot")) {
            numFootContacts--;
        }
    }

    public boolean playerCanJump(){
        return numFootContacts > 0;
    }

    public Array<Body> getObstacles(){
        return obstacles;
    }

    public boolean isPlayerDead() {
        return playerDead;
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
