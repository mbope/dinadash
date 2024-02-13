package com.mygdx.dinodash.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Content {
    private HashMap<String, Texture> textures;
    private HashMap<String, Music> music;
    private HashMap<String, Sound> sounds;

    public Content(){
        textures = new HashMap<String, Texture>();
        music = new HashMap<String, Music>();
        sounds = new HashMap<String, Sound>();
    }

    public void loadTexture (String path){
        int slashIndex = path.lastIndexOf("/");
        String key;
        if(slashIndex == -1){
            key = path.substring(0, path.lastIndexOf("."));
        }
        else{
            key = path.substring(slashIndex +1, path.lastIndexOf("."));
        }
        loadTexture(path, key);
    }

    public void loadTexture(String path, String key){
        Texture t = new Texture(path);
        textures.put(key, t);
    }

    public Texture getTextture(String key){
        return textures.get(key);
    }

    public void rmeoveTexture(String key){
        Texture t = textures.get(key);
        if(t != null){
            textures.remove(t);
            t.dispose();
        }
    }

    public void loadMusic(String path){
        int slashIndex = path.lastIndexOf("/");
        String key;
        if(slashIndex == -1){
            key = path.substring(0,path.lastIndexOf("."));
        }
        else{
            key = path.substring(slashIndex + 1, path.lastIndexOf("."));
        }
        loadMusic(path,key);
    }

    public void loadMusic(String path, String key){
        Music m = Gdx.audio.newMusic(Gdx.files.internal(path));
        music.put(key, m);
    }

    public Music getMusic(String key){
        return music.get(key);
    }

    public void removeMusic(String key){
        Music m = music.get(key);
        if(m != null){
            music.remove(key);
            m.dispose();
        }
    }

    public void loadSound(String path){
        int slashIndex = path.lastIndexOf("/");
        String key;
        if(slashIndex == -1){
            key = path.substring(0, path.lastIndexOf("."));
        }
        else{
            key = path.substring(slashIndex + 1, path.lastIndexOf("."));
        }
        loadSound(path, key);
    }

    public void loadSound(String path, String key){
        Sound s = Gdx.audio.newSound(Gdx.files.internal(path));
        sounds.put(key, s);
    }

    public void removeAll(){
        // remove all textures
        for(Texture t: textures.values()){
            t.dispose();;
        }
        textures.clear();
        // remove all musics
        for(Music m: music.values()){
            m.dispose();
        }
        music.clear();
        // remove all sounds
        for(Sound s: sounds.values()){
            s.dispose();
        }
        sounds.clear();
    }
}
