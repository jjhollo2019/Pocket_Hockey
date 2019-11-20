package com.example.pockethockey;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class HighScoreDatabase {
    private ArrayList<HighScoreEntry> scores;

    public HighScoreDatabase(Context context){
        scores = new ArrayList<>();

        for (int i = 0; i < 3; i++){
            scores.add(new HighScoreEntry());
            scores.get(i).fetchData(context, i + 1);
        }
    }

    public ArrayList<HighScoreEntry> getHighScores(){
        return scores;
    }

    /**
     *
     * @param level highest level player reached during game
     * @return true if new level reached should be set as a highscore, false otherwise
     */
    public boolean checkHighScores(Context context, int level){
        boolean updateScores = false;

        for (int i = 0; i < 3; i++){
            scores.get(i).fetchData(context, i + 1);
            if (level > scores.get(i).getScore()) updateScores = true;
        }

        return updateScores;
    }

    public void setNewHighScore(Context context, int level, String initials){
        for (int i = 0; i < 3; i++){
            scores.get(i).fetchData(context, i + 1);
        }

        int newRank = 0;
        // You don't need #3 anymore
        File oldfile = context.getFileStreamPath("highscore3.txt");
        oldfile.delete();
        // Check against #1
        if (level > scores.get(1).getScore()){
            newRank = 1;

            // Rename #2 to #3
            oldfile = context.getFileStreamPath("highscore2.txt");
            File newfile = context.getFileStreamPath("highscore3.txt");
            oldfile.renameTo(newfile);

            // Rename #1 to #2
            oldfile = context.getFileStreamPath("highscore1.txt");
            newfile = context.getFileStreamPath("highscore2.txt");
            oldfile.renameTo(newfile);
        }
        // Check against #2
        else if (level > scores.get(2).getScore()){
            newRank = 2;

            // Rename #2 to #3
            oldfile = context.getFileStreamPath("highscore2.txt");
            File newfile = context.getFileStreamPath("highscore3.txt");
            oldfile.renameTo(newfile);
        }
        // Check against #3
        else{
            newRank = 3;
        }

        try{
            // Write new data to its respective file
            FileOutputStream outputStream = context.openFileOutput("highscore" + newRank + ".txt", Context.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(outputStream);
            writer.println(initials);
            writer.println(level);
        }
        catch(IOException e){
            Log.e(TAG, "HighScoreDatabase: " + e.getMessage());
        }
    }
}
