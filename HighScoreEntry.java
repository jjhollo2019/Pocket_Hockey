package com.example.pockethockey;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static android.content.ContentValues.TAG;

public class HighScoreEntry {
    private int rank;
    private Drawable image;
    private int score;
    private String playerInitials;

    public HighScoreEntry(Context context, int r) {
        rank = r;
        String filename = "highscore" + r + ".txt";

        // Check if file exists
        File file = context.getFileStreamPath(filename);
        if(file == null || !file.exists()){
            // If it doesn't, initialize fields to defaults
            switch(r){
                case 1:
                    // Sabres
                    image = context.getDrawable(R.drawable.sprite_27);
                    playerInitials = "BUF";
                    break;
                case 2:
                    // Rangers
                    image = context.getDrawable(R.drawable.sprite_11);
                    playerInitials = "NYR";
                    break;
                case 3:
                    // Blackhawks
                    image = context.getDrawable(R.drawable.sprite_24);
                    playerInitials = "CHI";
                    break;
                default:
                    // This shouldn't happen
                    Log.e(TAG, "HighScoreEntry: Out of bounds rank (not 1 - 3)");
            }
            score = 0;
        }
        else{
            // If it does, read data from it
            FileInputStream inFile;
            try {
                inFile = context.openFileInput(filename);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public int getRank(){
        return rank;
    }

    public Drawable getImage(){
        return image;
    }

    public int getScore(){
        return score;
    }

    public String getPlayerInitials(){
        return playerInitials;
    }
}
