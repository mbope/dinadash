package com.mygdx.dinodash.states;

import static com.mygdx.dinodash.handlers.B2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.dinodash.MyGame;
import com.mygdx.dinodash.entity.Egg;
import com.mygdx.dinodash.entity.HUD;
import com.mygdx.dinodash.entity.Player;
import com.mygdx.dinodash.handlers.B2DVars;
import com.mygdx.dinodash.handlers.Background;
import com.mygdx.dinodash.handlers.BoundedCamera;
import com.mygdx.dinodash.handlers.DDContactListener;
import com.mygdx.dinodash.handlers.DDInput;
import com.mygdx.dinodash.handlers.GameStateManager;

public class Play extends GameState{
    private boolean debug = false;

    private World world;
    private Box2DDebugRenderer b2dRenderer;
    private DDContactListener dl;
    private BoundedCamera b2dCam;

    private Player player;

    private TiledMap tiledMap;
    private int tileMapWidth;
    private int tileMapHeight;
    private int tileSize;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    private Array<Egg> eggs;
    private Background[] backgrounds;
    private HUD hud;

    private static int level;

    public Play(GameStateManager gsm){
        super(gsm);
    }

    public void handleInput(){
        // Keyboard
        if(DDInput.isPressed(DDInput.BUTTON1)){
            playerJump();
        }
        if(DDInput.isPressed(DDInput.BUTTON2)){
            switchBlock();
        }
        // mouse/touch input for android
        // left side of screen  to switch block, right side to jump
        if(DDInput.isPressed()){
            if(DDInput.x < Gdx.graphics.getWidth() / 2){
                switchBlock();
            }
            else{
                playerJump();
            }
        }
    }
    public void update(float delta){
        // check input
        handleInput();

        // update box2d world
        world.step(MyGame.STEP,1,1);

        // check for collected eggs
        Array<Body> bodies = dl.getObstacles();
        for(int i=0; i< bodies.size; i++){
            Body body = bodies.get(i);
            eggs.removeValue((Egg) body.getUserData(),true);
            world.destroyBody(bodies.get(i));
            player.collectEgg();
            //MyGame.resources.getSound("egg").play();
        }
        bodies.clear();
        // update player
        player.update(delta);

        // check player win
        if(player.getBody().getPosition().x * PPM > tileMapWidth * tileSize){
            //MyGame.resources.getSound("levelselect").play();
            gsm.setState(GameStateManager.MENU);
        }
        // check player failed
        if(player.getBody().getPosition().y < 0) {
            //MyGame.resources.getSound("hit").play();
            gsm.setState(GameStateManager.MENU);
        }
        if(player.getBody().getLinearVelocity().x < 0.001f) {
            //MyGame.resources.getSound("hit").play();
            gsm.setState(GameStateManager.MENU);
        }
        if(dl.isPlayerDead()){
            //MyGame.resources.getSound("hit").play();
            gsm.setState(GameStateManager.MENU);
        }
        // update eggs
        for(int i=0; i < eggs.size; i++){
            eggs.get(i).update(delta);
        }
    }

    public void render(){
        // camera follow player
        cam.setPosition(player.getPosition().x *  +MyGame.V_WIDTH / 4,MyGame.V_HEIGHT / 2);
        cam.update();
        // draw backgrounds
        sb.setProjectionMatrix(hudCam.combined);
        for(int i=0; i < backgrounds.length; i++){
            backgrounds[i].render(sb);
        }
        //draw tilemap
        tiledMapRenderer.setView(cam);
        tiledMapRenderer.render();
        // draw player
        sb.setProjectionMatrix(cam.combined);
        player.render(sb);
        // draw eggs
        for(int i=0; i < eggs.size; i++){
            eggs.get(i).render(sb);
        }
        // draw hud
        sb.setProjectionMatrix(hudCam.combined);
        hud.render(sb);
        // debug draw box2d
        if(debug) {
            b2dCam.setPosition(player.getPosition().x + MyGame.V_WIDTH / 4 / PPM, MyGame.V_HEIGHT / 2 / PPM);
            b2dCam.update();
            b2dRenderer.render(world, b2dCam.combined);
        }
    }
    public void dispose(){

    }

    private void playerJump(){
        if(dl.playerCanJump()){
            player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x, 0);
            player.getBody().applyForceToCenter(0, 200, true);
            //MyGame.resources.getSound("jump").play();
        }
    }

    private void switchBlock(){

    }

    // Set up box2d bodies for eggs
    private void createEggs(){
        // create list of eggs
        eggs = new Array<Egg>();

        MapLayer ml = tiledMap.getLayers().get("eggs");
        if(ml == null){
            return;
        }
        for(MapObject mo: ml.getObjects()){
            BodyDef cdef = new BodyDef();
            cdef.type = BodyDef.BodyType.StaticBody;
            float x = (float) mo.getProperties().get("x") / PPM;
            float y = (float) mo.getProperties().get("y") / PPM;
            cdef.position.set(x, y);
            Body body = world.createBody(cdef);
            FixtureDef cfdef = new FixtureDef();
            CircleShape cshape = new CircleShape();
            cshape.setRadius(8 / PPM);
            cfdef.shape = cshape;
            cfdef.isSensor = true;
            cfdef.filter.categoryBits = B2DVars.BIT_CRYSTAL;
            cfdef.filter.maskBits = B2DVars.BIT_PLAYER;
            body.createFixture(cfdef).setUserData("egg");
            Egg e = new Egg(body);
            body.setUserData(e);
            eggs.add(e);
            cshape.dispose();
        }
    }

    /**
     * Creates box2d bodies for all non-null tiles
     * in the specified layer and assigns the specified
     * category bits.
     *
     * @param layer the layer being read
     * @param bits category bits assigned to fixtures
     */
    private void createBlocks(TiledMapTileLayer layer, short bits){
        // tile size
        float ts = layer.getTileWidth();
        // go throw all cells in layer
        for(int row = 0; row < layer.getHeight(); row++){
            for(int col = 0; col < layer.getWidth();col++){
                //get cell
                TiledMapTileLayer.Cell cell = layer.getCell(col, row);
                // check that there is a cell
                if(cell == null) continue;
                if(cell.getTile() == null) continue;
                // create body from Cell
                BodyDef bdef = new BodyDef();
                bdef.type = BodyDef.BodyType.StaticBody;
                bdef.position.set((col + 0.5f) * ts / PPM, (row + 0.5f) * ts / PPM);
                ChainShape cs = new ChainShape();
                Vector2[] v = new Vector2[3];
                v[0] = new Vector2(-ts / 2 / PPM, -ts / 2 / PPM);
                v[1] = new Vector2(-ts / 2 / PPM, ts / 2 / PPM);
                v[2] = new Vector2(ts / 2 / PPM, ts / 2 / PPM);
                cs.createChain(v);
                FixtureDef fd = new FixtureDef();
                fd.friction = 0;
                fd.shape = cs;
                fd.filter.categoryBits = bits;
                fd.filter.maskBits = B2DVars.BIT_PLAYER;
                world.createBody(bdef).createFixture(fd);
                cs.dispose();
            }
        }
    }
}
