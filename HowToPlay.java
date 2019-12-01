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
import android.widget.Button;
import android.widget.ImageView;

/**
 * This class handles the logic for the how to play screen
 */
public class HowToPlay extends AppCompatActivity {

    /**
     * This function overrides the AppCompat on create method
     * @param savedInstanceState = saved data
     * @pre This class must be declared as an activity in the manifest
     * @post The xml layout is loaded and button intents are set
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //call super
        super.onCreate(savedInstanceState);
        //set content
        setContentView(R.layout.activity_how_to_play);

        // Hide the status bar
        View decorView = getWindow().getDecorView();
        //set full screen
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        //set onclick method for the back button
        ImageView imageView = findViewById(R.id.backButtonHTP);
        imageView.setOnClickListener(new View.OnClickListener() {
            /**
             * This function overrides the onClickListener onClick method
             * @param view = the view container of the image
             */
            @Override
            public void onClick(View view) {
                //set intent
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //start activity
                startActivity(intent);
            }
        });
        //set onclick method for the new game button
        Button button = findViewById(R.id.newGameHTP);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * This function overrides the onClickListener onClick method
             * @param view = the view container of the image
             */
            @Override
            public void onClick(View view) {
                //set intent
                Intent intent = new Intent(getApplicationContext(), Splash.class);
                //start activity
                startActivity(intent);
            }
        });
    }
}
