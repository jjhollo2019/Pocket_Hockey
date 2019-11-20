package com.example.pockethockey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {
    private int strikes = 0;     // 3 strikes means GAME OVER
    private int level = 1;     // Start at Level 1
    private int highestLevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Hide the status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Assign views to activity variables
        TextView levelText = findViewById(R.id.levelTitle);
        TextView strikeText = findViewById(R.id.strikeReporter);

        // Grab result of level from Game.java
        Intent fromGame = getIntent();
        level = fromGame.getIntExtra("level", 1);
        strikes = fromGame.getIntExtra("strikes", 0);
        highestLevel = fromGame.getIntExtra("highestLevel", 1);

        // Update to new strikes and levels
        String s = getString(R.string.level) + level;
        levelText.setText(s);
        s = getString(R.string.strikeText) + strikes;
        strikeText.setText(s);
    }

    public void onReadyButtonClicked(View v){
        Intent i = new Intent(Splash.this, Game.class);
        i.putExtra("level", level);
        i.putExtra("strikes", strikes);
        i.putExtra("highestLevel", highestLevel);
        startActivity(i);
    }
}
