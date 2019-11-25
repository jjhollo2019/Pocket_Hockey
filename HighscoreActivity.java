/* Jeremy Holloway (jjhollo@clemson.edu, C20581376)
 * Zachary Amend (zamend@clemson.edu, C16422178)
 * CPSC-4150-001
 * 12/2/2019
 * Pocket Hockey
 */

package com.example.pockethockey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HighscoreActivity extends AppCompatActivity {
    /**
     * @param savedInstanceState current state of the application
     * @post inflates the activity layout
     * @post inflates the list fragment holding highscores
     */
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

        // Create fragment to hold the 3 saved highscores
        if (fragment == null) {
            fragment = new ListFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.scoreContainer, fragment)
                    .commit();
        }
    }

    /**
     * @param v the view (button) to listen to
     * @post intents to the title screen
     */
    public void goBackToHome(View v){
        Intent act = new Intent(HighscoreActivity.this, MainActivity.class);
        startActivity(act);
    }

    /**
     * @param v the view (button) to listen to
     * @post intents to the splash screen
     */
    public void startNewGame(View v){
        Intent act = new Intent(HighscoreActivity.this, Splash.class);
        startActivity(act);
    }
}
