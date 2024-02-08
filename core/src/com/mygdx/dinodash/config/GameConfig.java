package com.mygdx.dinodash.config;

public class GameConfig {
    public static final float WIDTH = 800f;//480f;
    public static final float HEIGHT = 480f;//800f;
    public static final float WORLD_WIDTH = 6.0f;
    public static final float WORLD_HEIGHT = 10.0f;
    public static final float WORLD_CENTER_X = WORLD_WIDTH / 2f;
    public static final float WORLD_CENTER_Y = WORLD_HEIGHT / 2f;
    public static final float PLAYER_SPEED = 0.25f;
    public static final float PLAYER_BOUNDS_RADIUS = 0.4f;
    public static final float PLAYER_SIZE = 2 * PLAYER_BOUNDS_RADIUS;

    public static final float OBSTACLE_BOUNDS_RADIUS = 0.3f;
    public static final float OBSTACLE_SIZE = 2 * OBSTACLE_BOUNDS_RADIUS;

    private GameConfig(){}
}
