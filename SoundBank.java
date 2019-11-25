package com.example.frameworktest;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * This class handles the sound logic for the game
 * @pre gameView != null && gameThread != null
 * @post SoundBank methods will be available for use
 */
public class SoundBank {

    //class variables
    Context context;
    MediaPlayer someNoise;

    /**
     * This is the class constructor
     * @param context = the context of the main application
     * @pre context != null
     * @post class variables are initialized 
     */
    public SoundBank(Context context){
        //set class variables
        this.context = context;
        someNoise = MediaPlayer.create(context, R.raw.swoosh);
    }

    /**
     * This function will play a sound
     * @pre someNoise != null
     * @post someNoise will play
     */
    public void playSomeNoise(){
        if(someNoise != null){
            someNoise.start();
        }
    }
}
