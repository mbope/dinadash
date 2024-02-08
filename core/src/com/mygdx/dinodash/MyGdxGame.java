package com.mygdx.dinodash;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.dinodash.screen.GameScreen;
import com.mygdx.dinodash.screen.MenuScreen;

public class MyGdxGame extends Game {
	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		//GameScreen gameScreen = new GameScreen(this);

		MenuScreen menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
	}
}

/*
public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
*/
