package com.example.pockethockey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class Game extends AppCompatActivity implements GameOver.OpenSelectedListener {
    private int strikes = 0;     // 3 strikes means GAME OVER
    private static int level = 1;     // Start at Level 1
    private static int highestLevel = 1;     // Start at Level 1, don't ever decrement
    private static boolean isGameOver;
    private static boolean highScoreSet;
    private HighScoreDatabase database;

    // Jeremy's variables
    private GamePanel gameView;
    FrameLayout game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GamePanel(this));     // TODO: Fix animation
        gameView = new GamePanel(this);
        game = new FrameLayout(this);
        database = new HighScoreDatabase(getApplicationContext());

        // Hide the status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        highScoreSet = false;
        isGameOver = false;

        // Grab strike and level data from Splash.java
        Intent fromSplash = getIntent();
        level = fromSplash.getIntExtra("level", 1);
        strikes = fromSplash.getIntExtra("strikes", 0);
        highestLevel = fromSplash.getIntExtra("highestLevel", 1);
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
                level = 1;
                strikes = 0;
                i = new Intent(Game.this, Splash.class);
                i.putExtra("level", level++);
                i.putExtra("strikes", strikes);
                break;
            // Negative button (Quit)
            case -2:
                // Jump back to the main activity
                i = new Intent(Game.this, MainActivity.class);
                break;
            // Neutral button
            case -3:
                // Jump to the leaderboard activity
                i = new Intent(Game.this, HighscoreActivity.class);
                break;
            default:
                // This shouldn't happen
                i = new Intent(Game.this, HowToPlay.class);
        }
        startActivity(i);
    }

    // If player scores
    private void onLevelPass(){
        // Pass data to the splash screen to display the next level
        Intent goToSplash = new Intent(Game.this, Splash.class);
        goToSplash.putExtra("level", level++);
        goToSplash.putExtra("strikes", strikes);
        goToSplash.putExtra("highestLevel", highestLevel++);
        startActivity(goToSplash);
    }

    // If player doesn't score (misses shot or doesn't shoot at all)
    private void onLevelFail(){
        strikes++;
        if (strikes == 3){
            onGameEnd();
            return;
        }
        if (level > 1) level--;

        // Send level and strikes to Splash.java
        Intent goToSplash = new Intent(Game.this, Splash.class);
        goToSplash.putExtra("level", level);
        goToSplash.putExtra("strikes", strikes);
        goToSplash.putExtra("highestLevel", highestLevel);
        startActivity(goToSplash);
    }

    private void onGameEnd(){
        isGameOver = true;

        FragmentManager manager = getSupportFragmentManager();
        if (!highScoreSet){
            // If the player got a new highscore, open HighScoreEnding
            if (database.checkHighScores(getApplicationContext(), highestLevel)){
                // Open the NewHighscoreDialog dialog
                NewHighscoreDialog dialog = new NewHighscoreDialog();
                // Don't allow players to click outside the dialog box (we don't want it to disappear)
                dialog.setCancelable(false);
                dialog.show(manager, "Game Over (New Highscore!)");
                highScoreSet = true;
                return;
            }
        }
        // In the case of no high score, open the GameOver dialog
        GameOver dialog = new GameOver();
        // Don't allow players to click outside the dialog box (we don't want it to disappear)
        dialog.setCancelable(false);
        dialog.show(manager, "Game Over (No Highscore)");
    }

    public static int getEndingLevel(){
        return level;
    }

    public static int getHighestLevel(){
        return highestLevel;
    }
}
