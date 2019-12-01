/* Jeremy Holloway (jjhollo@clemson.edu, C20581376)
 * Zachary Amend (zamend@clemson.edu, C16422178)
 * CPSC-4150-001
 * 12/2/2019
 * Flight Training
 */
package com.example.flight_training;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * This class is the main menu for the application
 * @pre none
 * @post main menu is displayed
 */
public class MainActivity extends AppCompatActivity {

    /**
     * This function adds our layout file and sets the onClick methods for the menu text
     * @param savedInstanceState = saved data for the method
     * @pre class must be called
     * @post menu is displayed to user
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //play theme song
        SoundBank soundBank = new SoundBank(getApplicationContext());
        soundBank.playTheme();

        //set full screen
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //set the new game button
        TextView newGame = findViewById(R.id.newGameTP);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Splash.class);
                startActivity(intent);
            }
        });

        //set the how to play button
        TextView htp = findViewById(R.id.howToPlayTP);
        htp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HowToPlay.class);
                startActivity(intent);
            }
        });

        //set the leaderboard button
        TextView lead = findViewById(R.id.leaderTP);
        lead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
                startActivity(intent);
            }
        });
    }
}
