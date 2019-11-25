package com.example.frameworktest;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank {

    private Bitmap background;
    private Bitmap[] playerRight;
    private Bitmap obstacle_top, obstacle_bottom;
    private Bitmap[] explosion;

    public BitmapBank(Resources res){
        background = BitmapFactory.decodeResource(res, R.drawable.background);
        background = scaleImage(background);

        playerRight = new Bitmap[2];
        playerRight[0] = BitmapFactory.decodeResource(res, R.drawable.plane_1);
        playerRight[0] = scalePlayer(playerRight[0]);
        playerRight[1] = BitmapFactory.decodeResource(res, R.drawable.plane_2);
        playerRight[1] = scalePlayer(playerRight[1]);

        obstacle_top = BitmapFactory.decodeResource(res, R.drawable.obs);
        obstacle_bottom = BitmapFactory.decodeResource(res, R.drawable.obs);

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

    public Bitmap getExplosion(int frame){
        return explosion[frame];
    }

    public Bitmap getObstacle_top() {
        return obstacle_top;
    }

    public Bitmap getObstacle_bottom() {
        return obstacle_bottom;
    }

    public int getObstacleWidth(){
        return obstacle_top.getWidth();
    }

    public int getObstacleHeight(){
        return obstacle_top.getHeight();
    }

    public Bitmap getPlayer(int frame){
        return playerRight[frame];
    }

    public int getPlayerWidth(){
        return playerRight[0].getWidth();
    }

    public int getPlayerHeight(){
        return playerRight[0].getHeight();
    }

    public Bitmap getBackground(){
        return background;
    }

    public int getBackgroundWidth(){
        return background.getWidth();
    }

    public int getBackgroundHeight(){
        return background.getHeight();
    }

    public Bitmap scaleImage(Bitmap bitmap){
        float widthHeightRatio = getBackgroundWidth()/getBackgroundHeight();
        int  backgroundScaledWidth = (int) widthHeightRatio * AppConstants.SCREEN_HEIGHT;
        return Bitmap.createScaledBitmap(bitmap, backgroundScaledWidth, AppConstants.SCREEN_HEIGHT, false);
    }

    public Bitmap scalePlayer(Bitmap bitmap){
        float widthHeightRatio = (getBackgroundWidth() / 4)/ (getBackgroundHeight() / 4);
        int  playerScaledWidth = (int) widthHeightRatio * (AppConstants.SCREEN_HEIGHT / 8);
        return Bitmap.createScaledBitmap(bitmap, playerScaledWidth, (AppConstants.SCREEN_HEIGHT / 8), false);
    }
}
