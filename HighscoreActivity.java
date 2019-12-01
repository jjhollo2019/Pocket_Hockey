/* Jeremy Holloway (jjhollo@clemson.edu, C20581376)
 * Zachary Amend (zamend@clemson.edu, C16422178)
 * CPSC-4150-001
 * 12/2/2019
 * Flight Training
 */
package com.example.flight_training;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * This class displays the leaderboard for the user
 * @pre must be intented to from another class
 * @post not values are changed by viewing the leaderboard
 */
public class HighscoreActivity extends AppCompatActivity {

    /**
     * This function will create the leaderboard and inflate the fragment
     * @param savedInstanceState = saved information
     * @pre must be called from another activity
     * @post leaderboard fragment is displayed for the user
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        // Hide the status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //get leaderboard fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.scoreContainer);

        //run transaction
        if (fragment == null) {
            fragment = new ListFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.scoreContainer, fragment)
                    .commit();
        }
    }

    /**
     *
     * @param v
     */
    public void goBackToHome(View v){
        Intent act = new Intent(HighscoreActivity.this, MainActivity.class);
        startActivity(act);
    }

    /**
     *
     * @param v
     */
    public void startNewGame(View v){
        Intent act = new Intent(HighscoreActivity.this, Splash.class);
        startActivity(act);
    }
}
