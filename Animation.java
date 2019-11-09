package com.example.pockethockey;

import android.graphics.Bitmap;

public class Animation {

    private Bitmap[] frames;
    private int currentFrame;
    private long startTime;
    private long delay;
    private boolean playOnce;

    public void setFrames(Bitmap[] frames){
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
    }

    public void setDelay(long d){delay = d;}
    public void setCurrentFrame(int i){currentFrame = i;}

    public void update(){
        long elapsed = (System.nanoTime() - startTime)/1000000;

        if(elapsed > delay){
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame == frames.length){
            currentFrame = 0;
            playOnce = true;
        }
    }

    public  Bitmap getImage(){
        return frames[currentFrame];
    }

    public int getCurrentFrame(){return currentFrame;}
    public boolean playOnce(){return playOnce;}
}
