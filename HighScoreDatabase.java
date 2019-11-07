package com.example.pockethockey;

import android.content.Context;
import java.util.ArrayList;

public class HighScoreDatabase {
    private ArrayList<HighScoreEntry> scores;

    public HighScoreDatabase(Context context){
        scores = new ArrayList<>();

        for (int i = 0; i < 3; i++){
            scores.add(new HighScoreEntry(context, i + 1));
        }
    }

    public ArrayList<HighScoreEntry> getHighScores(){
        return scores;
    }
}
