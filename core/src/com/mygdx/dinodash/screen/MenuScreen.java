package com.mygdx.dinodash.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.dinodash.assets.RegionNames;

public class MenuScreen extends BaseScreen{
    private static final Logger log = new Logger(MenuScreen.class.getName());
    public MenuScreen(Game game){
        super(game);
    }
    @Override
    public void create() {
        Table table = new Table();

        //TextureAtlas gamePlayAtlas = assetManager.get(AssetDescriptors.GAME_PLAY);
        //Skin uiSkin = assetManager.get(AssetDescriptors.UI_SKIN);

        TextureAtlas gamePlayAtlas = new TextureAtlas(Gdx.files.internal("ui/game/gameplay.atlas"));
        Skin uiSkin = new Skin(Gdx.files.internal("ui/skin.json"));

        TextureRegion backgroundRegion = gamePlayAtlas.findRegion(RegionNames.BACKGROUND);
        table.setBackground(new TextureRegionDrawable(backgroundRegion));

        TextButton playButton = new TextButton("PLAY", uiSkin);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                play();
            }
        });

        // settings button
        TextButton settingsButton = new TextButton("Einstellungen", uiSkin);
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                showSettings();
            }
        });

        TextButton faqButton = new TextButton("FAQs",uiSkin);
        faqButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                showFAQs();
            }
        });

        TextButton exitButton = new TextButton("EXIT", uiSkin);
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                quit();
            }
        });

        // Setup table
        Table buttonTable = new Table(uiSkin);
        buttonTable.defaults().pad(20);
        //buttonTable.setBackground(RegionNames.PANEL);

        buttonTable.add(playButton).colspan(3).row();
        buttonTable.padBottom(50);
        buttonTable.add(settingsButton);
        buttonTable.add(faqButton);
        buttonTable.add(exitButton).row();
        buttonTable.padTop(50);

        buttonTable.center();

        table.add(buttonTable);
        table.center();
        table.setFillParent(true);
        table.pack();

        //BaseActor background = new BaseActor();
        //background.setTextture(new Texture(Gdx.files.internal("startbg.png")));
        uiStage.addActor(table);


    }

    @Override
    public void update(float delta) {

    }

    private void play(){
        log.debug("play()");
        game.setScreen(new GameScreen(game));
    }

    private void showSettings(){
        log.debug("showSettings()");
    }

    private void showFAQs(){
        log.debug("showFAQs()");
    }

    private void quit(){
        log.debug("quit()");
        Gdx.app.exit();
    }
}
