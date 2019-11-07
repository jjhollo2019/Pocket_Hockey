package com.example.pockethockey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HighscoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        // Hide the status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.scoreContainer);

        if (fragment == null) {
            fragment = new ListFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.scoreContainer, fragment)
                    .commit();
        }
    }

    public void goBackToHome(View v){
        Intent act = new Intent(HighscoreActivity.this, MainActivity.class);
        startActivity(act);
    }

    public void startNewGame(View v){
        Intent act = new Intent(HighscoreActivity.this, Game.class);
        startActivity(act);
    }
}
