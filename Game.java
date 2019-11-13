package com.example.pockethockey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Game extends AppCompatActivity implements GameOver.OpenSelectedListener {
    private static int strikes;     // 3 strikes means GAME OVER
    private static int level;     // Start at Level 1, keep going until you drop
    private static boolean isGameOver;
    private static boolean highScoreSet;
    private Button readyBut;
    private TextView levelText;
    private TextView strikeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Hide the status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        highScoreSet = false;
        isGameOver = false;

        // Initialize strikes and level
        strikes = 0;
        level = 1;

        // Assign views to activity variable
        readyBut = findViewById(R.id.readyButton);
        levelText = findViewById(R.id.levelTitle);
        strikeText = findViewById(R.id.strikeReporter);

        initializeLevel();
    }

    @Override
    protected void onStart(){
        super.onStart();

        if (isGameOver) onGameEnd();
    }

    private void initializeLevel(){
        String s = getString(R.string.strikeText) + strikes;
        strikeText.setText(s);
        s = getString(R.string.level) + level;
        levelText.setText(s);

        // Show the level title and start button
        levelText.setVisibility(View.VISIBLE);
        strikeText.setVisibility(View.VISIBLE);
        readyBut.setVisibility(View.VISIBLE);
        readyBut.setEnabled(true);
    }

    public void startLevel(View view){
        // Hide the level title and start button
        readyBut.setEnabled(false);
        readyBut.setVisibility(View.GONE);
        strikeText.setVisibility(View.GONE);
        levelText.setVisibility(View.GONE);

        onGameEnd();
    }

    @Override
    public void onButtonChosen(int which){
        Intent i;
        switch(which){
            // Positive button (New Game)
            case -1:

                level++;
                // Restart this activity
                setIsGameOverToFalse();

                // TODO: Figure out if this calls onCreate() or not
                i = getIntent();
                finish();
                break;
            // Negative button (Quit)
            case -2:
                // Jump back to the main activity
                i = new Intent(Game.this, MainActivity.class);
                break;
            // TODO: Neutral button
            default:
                // Jump to the leaderboard activity
                i = new Intent(Game.this, HighscoreActivity.class);
        }
        startActivity(i);
    }

    private void onGameEnd(){
        isGameOver = true;

        FragmentManager manager = getSupportFragmentManager();
        if (highScoreSet){
            // If the player got a new highscore, open HighScoreEnding
            highScoreSet = true;
        }
        else{
            // If not, open GameOver
            GameOver dialog = new GameOver();
            // Don't allow players to click outside the dialog box (we don't want it to disappear)
            dialog.setCancelable(false);
            dialog.show(manager, "Game Over (No Highscore)");
        }
    }

    public static int getEndingLevel(){
        return level;
    }

    // This should be called before the start of any new game (we accomplish this with intents)
    public static void setIsGameOverToFalse(){
        isGameOver = false;
        highScoreSet = false;
    }
}
