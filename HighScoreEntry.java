package com.example.pockethockey;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
            image = context.getDrawable(R.drawable.plane_1);
        }
        catch(IOException e){
            // If it doesn't, initialize fields to defaults
            image = context.getDrawable(R.drawable.plane_1);
            playerInitials = "ABC";
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
