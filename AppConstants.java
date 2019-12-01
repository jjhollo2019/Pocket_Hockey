/* Jeremy Holloway (jjhollo@clemson.edu, C20581376)
 * Zachary Amend (zamend@clemson.edu, C16422178)
 * CPSC-4150-001
 * 12/2/2019
 * Flight Training
 */
package com.example.flight_training;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * This class handles static references for the majority of the game variables
 * @pre gameView != null && gameThread != null
 * @post The game variables will be ready for static references
 */
public class AppConstants {

    //static Bitmap instance for getting images
    private static BitmapBank bitmapBank;
    //static GameEngine instance for running the game
    private static GameEngine gameEngine;
    //static variable references
    static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static int gravity;
    static int VELOCITY_WHEN_JUMPED;
    static int OBSTACLE_GAP;
    static int numberOfObstacles;
    static int obstacleVelocity;
    static int minObstacleOffsetY;
    static int maxObstacleOffsetY;
    static int distanceBetweenObstacles;
    static int strikes;
    static int level;
    //static instance of the main class context
    static Context gameActivityContext;
    //static sound reference
    private static SoundBank soundBank;

    /**
     * This function is a sudo class constructor since the class only contains static references
     * @param context = the context of the main application
     * @pre context != null
     * @post bitmapBank, gameEngine, soundBank are initialized
     */
    public static void initialization(Context context){
        //set the screen size
        setScreenSize(context);
        //init bitmapBank
        bitmapBank = new BitmapBank(context.getResources());
        //set the game constants
        setGameConstants();
        //init game engine
        gameEngine = new GameEngine();
        //init soundBank
        soundBank = new SoundBank(context);
    }

    /**
     * This function will set the static constants for the game
     * @pre must be called before initializing the game engine
     */
    private static void setGameConstants(){
        //set class constants
        AppConstants.gravity = 2;
        AppConstants.VELOCITY_WHEN_JUMPED = -30;
        //if the level is less than 4, set for easy mode
        if(AppConstants.level < 4){
            AppConstants.distanceBetweenObstacles = AppConstants.SCREEN_WIDTH * 5 / 6;
            AppConstants.numberOfObstacles = 2;
            AppConstants.OBSTACLE_GAP = 400;
        //else set for hard mode
        } else {
            AppConstants.distanceBetweenObstacles = AppConstants.SCREEN_WIDTH * 3 / 4;
            AppConstants.numberOfObstacles = AppConstants.level;
            AppConstants.OBSTACLE_GAP = 300;
        }
        //set remaining variables
        AppConstants.obstacleVelocity = 12;
        AppConstants.minObstacleOffsetY = (int) (AppConstants.OBSTACLE_GAP / 2.0);
        AppConstants.maxObstacleOffsetY = AppConstants.SCREEN_HEIGHT - AppConstants.minObstacleOffsetY - AppConstants.OBSTACLE_GAP;
    }

    /**
     * This is the getter for the soundBank
     * @return soundBank
     * @pre soundBank != null
     * @post the instance of soundBank is not changed
     */
    public static SoundBank getSoundBank() {
        return soundBank;
    }

    /**
     * This is the getter for the bitmapBank
     * @return bitmapBank
     * @pre bitmapBank != null
     * @post the instance of bitmapBank is not changed
     */
    public static BitmapBank getBitmapBank() {
        return bitmapBank;
    }

    /**
     * This is the getter for the same engine
     * @return = gameEngine
     * @pre gameEngine != null
     * @post the insatnce of gameEngine is not changed
     */
    public static GameEngine getGameEngine() {
        return gameEngine;
    }

    /**
     * This function determines and sets the screen size
     * @param context = the main activity context
     * @pre context != null
     * @post SCREEN_WIDTH && SCREEN_HEIGHT are set
     */
    private  static void setScreenSize(Context context){
        //get the window manager
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //get the display
        Display display = windowManager.getDefaultDisplay();
        //get display metrics
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        //get the pixel width and height from the display metrics
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        //set class variables
        AppConstants.SCREEN_WIDTH = width;
        AppConstants.SCREEN_HEIGHT = height;
    }
}
