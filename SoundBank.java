package com.example.planeshooter;

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
     * This function will play a sound
     * @pre someNoise != null
     * @post someNoise will play
     */
    public void playPlane(){
        if(!planeSound.isPlaying()){
            planeSound.start();
        }
    }

    public void stopPlane(){
        if(planeSound.isPlaying()){
            planeSound.stop();
        }
    }

    public void playTheme(){
        if(!theme.isPlaying()){
            theme.start();
        }
    }

    public void stoptheme(){
        if(theme.isPlaying()){
            theme.stop();
        }
    }

    public void playSplash(){
        if(!splashMusic.isPlaying()){
            splashMusic.start();
        }
    }

    public void stopSplash(){
        if(splashMusic.isPlaying()){
            splashMusic.stop();
        }
    }
}
