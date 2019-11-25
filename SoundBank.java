package com.example.frameworktest;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundBank {

    Context context;
    MediaPlayer someNoise;

    public SoundBank(Context context){
        this.context = context;
        someNoise = MediaPlayer.create(context, R.raw.swoosh);
    }

    public void playSomeNoise(){
        if(someNoise != null){
            someNoise.start();
        }
    }
}
