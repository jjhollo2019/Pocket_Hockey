/* Jeremy Holloway (jjhollo@clemson.edu, C20581376)
 * Zachary Amend (zamend@clemson.edu, C16422178)
 * CPSC-4150-001
 * 12/2/2019
 * Flight Training
 */

package com.example.pockethockey;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

/**
 * This class contains the top 3 highscore items in a list fragment to easily be displayed after
 * updating.
 */
public class ListFragment extends Fragment {
    /**
     * @param inflater that inflates the fragment and displays it to the screen
     * @param container view that contains each item in the fragment
     * @param savedInstanceState current state of the application
     * @return a view of the fragment with all of its items to be displayed
     * @post creates and returns a view of the fragment with all of its items to be displayed
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        LinearLayout layout = (LinearLayout) view;

        // Grab the current high scores
        ArrayList<HighScoreEntry> scoreList = new HighScoreDatabase(getContext()).getHighScores();
        for (int i = 0; i < scoreList.size(); i++) {
            // Grab highscore item
            HighScoreEntry score = scoreList.get(i);
            // Get a blank layout for this item
            View layoutItem = inflater.inflate(R.layout.highscore_item, container, false);

            // Get rank
            TextView rank = layoutItem.findViewById(R.id.rankEntry);
            rank.setText(Integer.toString(score.getRank()) + ".");
            // Get picture
            ImageView img = layoutItem.findViewById(R.id.cameraPic);
            img.setImageDrawable(score.getImage());
            // Get level score
            TextView levelScore = layoutItem.findViewById(R.id.scoreEntry);
            levelScore.setText(Integer.toString(score.getScore()));
            // Get player initials
            TextView playerName = layoutItem.findViewById(R.id.nameEntry);
            playerName.setText(score.getPlayerInitials().toUpperCase());

            // Add entry to list fragment
            layout.addView(layoutItem);
        }

        return view;
    }
}
