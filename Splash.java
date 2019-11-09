package com.example.pockethockey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //set this to the ready screen you made
        setContentView(R.layout.activity_how_to_play);

        Thread myThread = new Thread(){
            @Override
            public void run(){
                try{
                    sleep(4000);
                    Intent startgame = new Intent(getApplicationContext(), Game.class);
                    startActivity(startgame);
                    finish();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();

    }



}
