/* Jeremy Holloway (jjhollo@clemson.edu, C20581376)
 * Zachary Amend (zamend@clemson.edu, C16422178)
 * CPSC-4150-001
 * 12/2/2019
 * Flight Training
 */
package com.example.flight_training;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * This class will hold all of the image instances for the game
 * @pre drawable files must be present in memory
 * @post images are available for use
 */
public class BitmapBank {

    //background instance
    private Bitmap background;
    //player instance
    private Bitmap[] player;
    //obstacle instance
    private Bitmap obstacle_top, obstacle_bottom;
    //explosion instance
    private Bitmap[] explosion;

    /**
     * This function is the class constructor
     * @param res = the resource reference for the main activity
     * @pre res != null
     * @post all class instances are initialized
     */
    public BitmapBank(Resources res){
        //get and scale background
        background = BitmapFactory.decodeResource(res, R.drawable.background);
        background = scaleImage(background);
        //get and scale player
        player = new Bitmap[2];
        player[0] = BitmapFactory.decodeResource(res, R.drawable.plane_1);
        player[0] = scalePlayer(player[0]);
        player[1] = BitmapFactory.decodeResource(res, R.drawable.plane_2);
        player[1] = scalePlayer(player[1]);
        //get obstacle
        obstacle_top = BitmapFactory.decodeResource(res, R.drawable.obs);
        obstacle_bottom = BitmapFactory.decodeResource(res, R.drawable.obs);
        //get explosion
        explosion = new Bitmap[9];
        explosion[0] = BitmapFactory.decodeResource(res, R.drawable.explosion0);
        explosion[1] = BitmapFactory.decodeResource(res, R.drawable.explosion1);
        explosion[2] = BitmapFactory.decodeResource(res, R.drawable.explosion2);
        explosion[3] = BitmapFactory.decodeResource(res, R.drawable.explosion3);
        explosion[4] = BitmapFactory.decodeResource(res, R.drawable.explosion4);
        explosion[5] = BitmapFactory.decodeResource(res, R.drawable.explosion5);
        explosion[6] = BitmapFactory.decodeResource(res, R.drawable.explosion6);
        explosion[7] = BitmapFactory.decodeResource(res, R.drawable.explosion7);
        explosion[8] = BitmapFactory.decodeResource(res, R.drawable.explosion8);
    }

    /**
     * This function is a getter for the obstacle_top instance
     * @return obstacle_top
     * @pre obstacle_top != null
     * @post the instance of obstacle_top is not changed
     */
    public Bitmap getObstacle_top() {
        return obstacle_top;
    }

    /**
     * This function is a getter for the obstacle_bottom instance
     * @return obstacle_bottom
     * @pre obstacle_bottom != null
     * @post the instance of obstacle_bottom is not changed
     */
    public Bitmap getObstacle_bottom() {
        return obstacle_bottom;
    }

    /**
     * This function is a getter for the obstacle width
     * @return the obstacle width, top and bottom are the same size
     * @pre obstacle_top != null
     * @post obstacle_top is not changed
     */
    public int getObstacleWidth(){
        return obstacle_top.getWidth();
    }

    /**
     * This function is a getter for the obstacle height
     * @return the obstacle height
     * @pre obstacle_top != null
     * @post obstacle_top is not changed
     */
    public int getObstacleHeight(){
        return obstacle_top.getHeight();
    }

    /**
     * This function is a getter object for the player instance
     * @param frame = the frame of animation to get the player
     * @return the player image at the specified frame
     * @pre player != null
     * @post player instance is not changed
     */
    public Bitmap getPlayer(int frame){
        return player[frame];
    }

    /**
     * This function is the getter for the player width
     * @return the width of the first frame, they should all be the same size
     * @pre player != null
     * @post player instance is unchanged
     */
    public int getPlayerWidth(){
        return player[0].getWidth();
    }

    /**
     * This function is the getter for the player height
     * @return the height of the first frame, they should all be the same size
     * @pre player != null
     * @post player instance is unchanged
     */
    public int getPlayerHeight(){
        return player[0].getHeight();
    }

    /**
     * This function is the getter for the background
     * @return background instance
     * @pre background != null
     * @post backgroudn instance is unchanged
     */
    public Bitmap getBackground(){
        return background;
    }

    /**
     * This function is a getter for the background width
     * @return the width of the background image
     * @pre background != null
     * @post background instance is unchanged
     */
    public int getBackgroundWidth(){
        return background.getWidth();
    }

    /**
     * This function is a getter for the background height
     * @return the height of the background image
     * @pre background != null
     * @post background instance is unchanged
     */
    public int getBackgroundHeight(){
        return background.getHeight();
    }

    /**
     * This function will scale the background image to fit the screen
     * @param bitmap = the bitmap to be scaled
     * @return the bitmap scaled to proper dimensions
     * @pre bitmap != null && AppConstants must be initialized
     * @post the bitmap is rescaled based on screen size
     */
    public Bitmap scaleImage(Bitmap bitmap){
        //calculate the width to height ratio
        float widthHeightRatio = getBackgroundWidth()/getBackgroundHeight();
        //scale the width
        int  backgroundScaledWidth = (int) widthHeightRatio * AppConstants.SCREEN_HEIGHT;
        //create the scaled image
        return Bitmap.createScaledBitmap(bitmap, backgroundScaledWidth, AppConstants.SCREEN_HEIGHT, false);
    }

    /**
     * This function will scale the player image to fit the screen
     * @param bitmap = the bitmap to be scaled
     * @return the scaled bitmap
     * @pre bitmap != null && AppConstants must be initialized
     * @post the bitmap is rescaled based on the screen size
     */
    public Bitmap scalePlayer(Bitmap bitmap){
        //calculate the width to height ratio
        float widthHeightRatio = (getBackgroundWidth() / 4)/ (getBackgroundHeight() / 4);
        //scale the width
        int  playerScaledWidth = (int) widthHeightRatio * (AppConstants.SCREEN_HEIGHT / 8);
        //create the scaled image
        return Bitmap.createScaledBitmap(bitmap, playerScaledWidth, (AppConstants.SCREEN_HEIGHT / 8), false);
    }
}
