package com.example.pockethockey;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

public class GameOver extends DialogFragment {
    public interface OpenSelectedListener {
        void onButtonChosen(int which);
    }

    private GameOver.OpenSelectedListener myListener;

    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.gameOverTitle)
                .setMessage("Your Score: " + Game.getEndingLevel())
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myListener = (OpenSelectedListener) context;
    }
}
