/* Jeremy Holloway (jjhollo@clemson.edu, C20581376)
 * Zachary Amend (zamend@clemson.edu, C16422178)
 * CPSC-4150-001
 * 12/2/2019
 * Pocket Hockey
 */

package com.example.pockethockey;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HighScoreDatabase {
    private ArrayList<HighScoreEntry> scores;

    /**
     * @param context current context of the application
     * @post creates a new database containing values for the 3 highscores
     */
    public HighScoreDatabase(Context context){
        scores = new ArrayList<>();

        for (int i = 0; i < 3; i++){
            scores.add(new HighScoreEntry());
            scores.get(i).fetchData(context, i + 1);
        }
    }

    /**
     * @return scores
     * @post returns scores
     */
    public ArrayList<HighScoreEntry> getHighScores(){
        return scores;
    }

    /**
     * @pre 0 < level
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

    // TODO: Camera API and javadoc
    public void setNewHighScore(Context context, Integer level, String initials){
        for (int i = 0; i < 3; i++){
            scores.get(i).fetchData(context, i + 1);
        }

        File directory = new File(context.getFilesDir(), "highscores");
        // Create a directory to hold the highscores
        if (!directory.exists()) directory.mkdir();

        int newRank;
        // You don't need #3 anymore
        File oldfile = new File(directory, "highscore3.txt");
        oldfile.delete();
        // Check against #1
        if (level > scores.get(0).getScore()){
            newRank = 1;

            // Rename #2 to #3
            oldfile = new File(directory, "highscore2.txt");
            File newfile = new File(directory, "highscore3.txt");
            oldfile.renameTo(newfile);

            // Rename #1 to #2
            oldfile = new File(directory, "highscore1.txt");
            newfile = new File(directory, "highscore2.txt");
            oldfile.renameTo(newfile);
        }
        // Check against #2
        else if (level > scores.get(1).getScore()){
            newRank = 2;

            // Rename #2 to #3
            oldfile = new File(directory, "highscore2.txt");
            File newfile = new File(directory, "highscore3.txt");
            oldfile.renameTo(newfile);
        }
        // Replace #3
        else{
            newRank = 3;
        }

        try{
            // Write new data to its respective file
            File outFile = new File(directory, "highscore" + newRank + ".txt");
            FileWriter writer = new FileWriter(outFile);
            String addNewLine = initials + "\n";
            writer.append(addNewLine);
            writer.append(level.toString());
            writer.flush();
            writer.close();
            Log.e("HighScoreDatabase", "File written");
        }
        catch(IOException e){
            Log.e("HighScoreDatabase", "Exception: " + e.getMessage());
        }
    }
}
