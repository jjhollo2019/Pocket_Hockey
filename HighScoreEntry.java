package com.example.pockethockey;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static android.content.ContentValues.TAG;

public class HighScoreEntry {
    private int rank;
    private Drawable image;
    private int score;
    private String playerInitials;

    public void fetchData(Context context, int r) {
        rank = r;
        String filename = "highscore" + r + ".txt";
        // Open correct file to read from
        File directory = new File(context.getFilesDir(), "highscores");
        File inFile = new File(directory, filename);

        // Check if file exists
        try{
            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            // First, read initials
            playerInitials = reader.readLine();
            // Second, read actual score
            score = Integer.parseInt(reader.readLine());
            // Set a default image for now
            switch(r){
                case 1:
                    // Sabres
                    image = context.getDrawable(R.drawable.sprite_27);
                    break;
                case 2:
                    // Rangers
                    image = context.getDrawable(R.drawable.sprite_11);
                    break;
                case 3:
                    // Blackhawks
                    image = context.getDrawable(R.drawable.sprite_24);
                    break;
                default:
                    // This shouldn't happen
                    Log.e(TAG, "HighScoreEntry: Out of bounds rank (not 1 - 3)");
            }
        }
        catch(IOException e){
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
