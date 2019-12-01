/* Jeremy Holloway (jjhollo@clemson.edu, C20581376)
 * Zachary Amend (zamend@clemson.edu, C16422178)
 * CPSC-4150-001
 * 12/2/2019
 * Flight Training
 */
package com.example.flight_training;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

/**
 * This class handles the end of game events and extends the dialogFragment to interact with the user
 * @pre This class must extend DialogFragment
 * @post The class is able to handle end game checking via a Dialog Fragment
 */
public class GameOver extends DialogFragment {

    /**
     * This interface is for the listener
     * @pre class must extend DialogFragment
     * @post onButtonChosen is available for use
     */
    public interface OpenSelectedListener {
        void onButtonChosen(int which);
    }

    //declare listener
    private OpenSelectedListener myListener;

    /**
     * This is the onCreate function for the Dialog
     * @param savedInstanceState = saved variables
     * @return created dialog
     */
    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                //set dialog title
                .setTitle(R.string.gameOverTitle)
                //set dialog message
                .setMessage("Your Score: " + Splash.getEndingLevel())
                //set the onclick for the positive response
                .setPositiveButton(R.string.playAgainDialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked New Game
                        myListener.onButtonChosen(id);
                    }
                })
                //set negative response onClick
                .setNegativeButton(R.string.quitDialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked Quit
                        myListener.onButtonChosen(id);
                    }
                })
                //set neutral response for onClick
                .setNeutralButton(R.string.leaderboardDialog, new DialogInterface.OnClickListener() {
                    @Override
                    // User clicked Leaderboard
                    public void onClick(DialogInterface dialogInterface, int id) {
                        myListener.onButtonChosen(id);
                    }
                })
                //final call to create fragment
                .create();
    }

    /**
     * The function overrides the onAttach to allow for the custom listener
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //set listener
        myListener = (OpenSelectedListener) context;
    }
}
