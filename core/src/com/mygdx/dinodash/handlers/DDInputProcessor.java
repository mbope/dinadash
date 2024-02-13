package com.mygdx.dinodash.handlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class DDInputProcessor extends InputAdapter {
    @Override
    public boolean mouseMoved(int x, int y){
        DDInput.x = x;
        DDInput.y = y;
        return true;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer){
        DDInput.x = x;
        DDInput.y = y;
        DDInput.down = true;
        return  true;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button){
        DDInput.x = x;
        DDInput.y = y;
        DDInput.down = true;
        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button){
        DDInput.x = x;
        DDInput.y = y;
        DDInput.down = false;
        return true;
    }

    public boolean keyDown(int k){
        if(k == Input.Keys.Z){
            DDInput.setKey(DDInput.BUTTON1, true);
        }
        if(k == Input.Keys.X){
            DDInput.setKey(DDInput.BUTTON2, true);
        }
        return true;
    }

    public boolean keyUp(int k){
        if(k == Input.Keys.Z){
            DDInput.setKey(DDInput.BUTTON1, false);
        }
        if(k == Input.Keys.X){
            DDInput.setKey(DDInput.BUTTON2, false);
        }
        return true;
    }
}
