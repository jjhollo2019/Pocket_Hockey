/* Jeremy Holloway (jjhollo@clemson.edu, C20581376)
 * Zachary Amend (zamend@clemson.edu, C16422178)
 * CPSC-4150-001
 * 12/2/2019
 * Pocket Hockey
 */

package com.example.pockethockey;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

public class GameOver extends DialogFragment {
    public interface OpenSelectedListener {
        /**
         * @pre 0 < which < 4
         * @param which id of button chosen from GameOver dialog box
         * @post intents to the respective activity
         */
        void onButtonChosen(int which);
    }

    // Sends which button was selected to the hosting activity
    private GameOver.OpenSelectedListener myListener;

    /**
     * @param savedInstanceState current state of the application
     * @return the newly created dialog box
     * @post sets outputs for each respective button available
     */
    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.gameOverTitle)
                .setMessage("Your Score: " + Splash.getEndingLevel())
                .setPositiveButton(R.string.playAgainDialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked New Game
                        myListener.onButtonChosen(id);
                    }
                })
                .setNegativeButton(R.string.quitDialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked Quit
                        myListener.onButtonChosen(id);
                    }
                })
                .setNeutralButton(R.string.leaderboardDialog, new DialogInterface.OnClickListener() {
                    @Override
                    // User clicked Leaderboard
                    public void onClick(DialogInterface dialogInterface, int id) {
                        myListener.onButtonChosen(id);
                    }
                })
                .create();
    }

    /**
     * @param context current context of the application
     * @post attaches dialog with listener to host activity
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myListener = (OpenSelectedListener) context;
    }
}
