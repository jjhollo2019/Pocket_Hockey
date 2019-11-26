/* Jeremy Holloway (jjhollo@clemson.edu, C20581376)
 * Zachary Amend (zamend@clemson.edu, C16422178)
 * CPSC-4150-001
 * 12/2/2019
 * Pocket Hockey
 */

package com.example.pockethockey;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class Splash extends AppCompatActivity implements GameOver.OpenSelectedListener{
    private int strikes = 0;     // 3 strikes means GAME OVER
    private static int level = 1;     // Start at Level 1
    private static boolean highScoreSet;

    /**
     * @param savedInstanceState current state of the application
     * @post inflates the layout activity
     * @post sets private data from data received via intent
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Hide the status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        highScoreSet = false;

        // Assign views to activity variables
        TextView levelText = findViewById(R.id.levelTitle);
        TextView strikeText = findViewById(R.id.strikeReporter);

        // Grab result of level from Game.java
        Intent fromGame = getIntent();
        level = fromGame.getIntExtra("level", 1);
        strikes = fromGame.getIntExtra("strikes", 0);

        // Update to new strikes and levels
        String s = getString(R.string.level) + level;
        levelText.setText(s);
        s = getString(R.string.strikeText) + strikes;
        strikeText.setText(s);

        if (strikes == 3) onGameEnd();
    }

    @Override
    protected void onStart(){
        super.onStart();

        if (highScoreSet){
            FragmentManager fragmentManager = getSupportFragmentManager();
            // In the case of no high score, open the GameOver dialog
            GameOver dialog = new GameOver();
            // Don't allow players to click outside the dialog box (we don't want it to disappear)
            dialog.setCancelable(false);
            dialog.show(fragmentManager, "Game Over (No Highscore)");
        }
    }

    private void onGameEnd(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        HighScoreDatabase database = new HighScoreDatabase(getApplicationContext());
        highScoreSet = true;

        // If the player got a new highscore, open HighScoreEnding
        if (database.checkHighScores(getApplicationContext(), level)){
            // Open the NewHighscoreDialog dialog
            NewHighscoreDialog dialog = new NewHighscoreDialog();
            // Don't allow players to click outside the dialog box (we don't want it to disappear)
            dialog.setCancelable(false);
            dialog.show(fragmentManager, "Game Over (New Highscore!)");
        }
        else {
            // In the case of no high score, open the GameOver dialog
            GameOver dialog = new GameOver();
            // Don't allow players to click outside the dialog box (we don't want it to disappear)
            dialog.setCancelable(false);
            dialog.show(fragmentManager, "Game Over (No Highscore)");
        }
    }

    /**
     * @pre 0 < which < 4
     * @param which id of button chosen from GameOver dialog box
     */
    @Override
    public void onButtonChosen(int which){
        Intent i;
        switch(which){
            // Positive button (New Game)
            case -1:
                // Reset level and strikes
                i = getIntent();
                i.putExtra("level", level);
                i.putExtra("strikes", strikes);
                break;
            // Negative button (Quit)
            case -2:
                // Jump back to the main activity
                i = new Intent(Splash.this, MainActivity.class);
                break;
            // Neutral button
            case -3:
                // Jump to the leaderboard activity
                i = new Intent(Splash.this, HighscoreActivity.class);
                break;
            default:
                // This shouldn't happen
                i = new Intent(Splash.this, HowToPlay.class);
        }
        startActivity(i);
    }

    /**
     * @param v the view (button) to listen to
     * @post sends level, strikes, and highestLevel in an intent to the game
     */
    public void onReadyButtonClicked(View v){
        Intent i = new Intent(Splash.this, Game.class);
        i.putExtra("level", level);
        i.putExtra("strikes", strikes);
        startActivity(i);
    }

    public static int getEndingLevel(){
        return level;
    }
}
