package com.example.frameworktest;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class AppConstants {

    static BitmapBank bitmapBank;
    static GameEngine gameEngine;
    static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static int gravity;
    static int VELOCITY_WHEN_JUMPED;
    static int OBSTACLE_GAP;
    static int numberOfObstacles;
    static int obstacleVelocity;
    static int minObstacleOffsetY;
    static int maxObstacleOffsetY;
    static int distanceBetweenObstacles;
    static Context gameActivityContext;
    static SoundBank soundBank;

    public static void initialization(Context context){
        setScreenSize(context);
        bitmapBank = new BitmapBank(context.getResources());
        setGameConstants();
        gameEngine = new GameEngine();
        soundBank = new SoundBank(context);
    }

    public static void setGameConstants(){
        AppConstants.gravity = 2;
        AppConstants.VELOCITY_WHEN_JUMPED = -30;
        AppConstants.OBSTACLE_GAP = 600;
        AppConstants.numberOfObstacles = 6;
        AppConstants.obstacleVelocity = 12;
        AppConstants.minObstacleOffsetY = (int) (AppConstants.OBSTACLE_GAP / 2.0);
        AppConstants.maxObstacleOffsetY = AppConstants.SCREEN_HEIGHT - AppConstants.minObstacleOffsetY - AppConstants.OBSTACLE_GAP;
        AppConstants.distanceBetweenObstacles = AppConstants.SCREEN_WIDTH * 3 /4;
    }

    public static SoundBank getSoundBank() {
        return soundBank;
    }

    public static BitmapBank getBitmapBank() {
        return bitmapBank;
    }

    public static GameEngine getGameEngine() {
        return gameEngine;
    }

    private  static void setScreenSize(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        AppConstants.SCREEN_WIDTH = width;
        AppConstants.SCREEN_HEIGHT = height;
    }
}
