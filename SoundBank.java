/* Jeremy Holloway (jjhollo@clemson.edu, C20581376)
 * Zachary Amend (zamend@clemson.edu, C16422178)
 * CPSC-4150-001
 * 12/2/2019
 * Pocket Hockey
 */
package com.example.flight_training;

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
    MediaPlayer planeSound;
    MediaPlayer theme;
    MediaPlayer splashMusic;

    /**
     * This is the class constructor
     * @param context = the context of the main application
     * @pre context != null
     * @post class variables are initialized
     */
    public SoundBank(Context context){
        //set class variables
        this.context = context;
        planeSound = MediaPlayer.create(context, R.raw.prop_sound);
        theme = MediaPlayer.create(context, R.raw.theme);
        splashMusic = MediaPlayer.create(context, R.raw.intermediate);
    }

    /**
     * This function will play planeSound
     * @pre planeSound != null
     * @post planeSound will play
     */
    public void playPlane(){
        if(!planeSound.isPlaying()){
            planeSound.start();
        }
    }

    /**
     * This function will stop planeSound
     * @pre planeSound != null
     * @post planeSound will stop
     */
    public void stopPlane(){
        if(planeSound.isPlaying()){
            planeSound.stop();
        }
    }

    /**
     * This function will play theme
     * @pre theme != null
     * @post theme will play
     */
    public void playTheme(){
        if(!theme.isPlaying()){
            theme.start();
        }
    }

    /**
     * This function will play splashMusic
     * @pre splashMusic != null
     * @post splashMusic will play
     */
    public void playSplash(){
        if(!splashMusic.isPlaying()){
            splashMusic.start();
        }
    }

    /**
     * This function will stop splashMusic
     * @pre splashMusic != null
     * @post splashMusic will stop
     */
    public void stopSplash(){
        if(splashMusic.isPlaying()){
            splashMusic.stop();
        }
    }
}
