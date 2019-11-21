package com.example.pockethockey;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Player extends GameObject{
    final private float RIGHT_EDGE = 723;

    private Bitmap spritesheet;
    private boolean isShooting;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;

    public Player(Bitmap res, int w, int h, int numFrames){
        x = GamePanel.WIDTH/2;
        y = GamePanel.HEIGHT/2;
        height = h;
        width = w;

        isShooting = false;
        if(!isShooting){
            numFrames = 2;
        }

        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;

        for(int i = 0; i < image.length; i++){
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(10);

        startTime = System.nanoTime();
    }

    public void setShooting(boolean b) {
        isShooting = b;
        Bitmap[] image = new Bitmap[4];
        for(int i = 0; i < image.length; i++){
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }
        animation.setFrames(image);
        animation.setDelay(10);
    }

    public void update(){
        long elapsed = (System.nanoTime() - startTime)/1000000;

        if(elapsed > 100){
            startTime = System.nanoTime();
        }
        animation.update();
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(animation.getImage(), x, y, null);
    }

    public boolean getPlaying(){return playing;}
    public void setPlaying(boolean b){playing = b;}
}
