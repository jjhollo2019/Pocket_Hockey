/* Jeremy Holloway (jjhollo@clemson.edu, C20581376)
 * Zachary Amend (zamend@clemson.edu, C16422178)
 * CPSC-4150-001
 * 12/2/2019
 * Pocket Hockey
 */

package com.example.pockethockey;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

public class NewHighscoreDialog extends DialogFragment {
    /**
     * @param savedInstanceState current state of the application
     * @return the newly created dialog box
     * @post sets outputs for each respective button available
     * @post sends highscore to the database if user presses "submit"
     */
    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
        final LayoutInflater inflater = requireActivity().getLayoutInflater();
        final EditText in = new EditText(getActivity());
        in.setHint(R.string.initials);
        in.setAutofillHints(getString(R.string.initials));
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        b.setView(in);
        b.setTitle(R.string.winningTitle);
        b.setMessage("Your Score: " + Splash.getEndingLevel());
        b.setPositiveButton(R.string.submitInitials, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked Submit
                // Grab initials from EditText
                String initials = in.getText().toString();
                // If the string entered is too long, just take the first 3 characters
                if (initials.length() > 3){
                    char[] seq = initials.toCharArray();
                    initials = "";
                    for (int i = 0; i < 3; i++){
                        initials += seq[i];
                    }
                }

                // Set new high score in database
                HighScoreDatabase database = new HighScoreDatabase(getContext());
                database.setNewHighScore(getContext(), Splash.getEndingLevel(), initials);

                // Intent to the leaderboard after storing highscore
                Intent i = new Intent(getContext(), HighscoreActivity.class);
                startActivity(i);
            }
        });
        b.setNegativeButton(R.string.cancelDialog, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked Cancel
                // Go back to the home screen
                Intent i = new Intent(getContext(), MainActivity.class);
                startActivity(i);
            }
        });

        return b.create();
    }
}
