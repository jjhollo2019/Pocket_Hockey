/* Jeremy Holloway (jjhollo@clemson.edu, C20581376)
<<<<<<< HEAD
 * Zachary Amend (zamend@clemson.edu, C16422178)
=======
 * Zachary Amend (zamend@clemson.edu, TODO ADD CUID)
>>>>>>> f1b5164691a3f1d9c2425b48de5a3f507547a27c
 * CPSC-4150-001
 * 12/2/2019
 * Final Project
 */
<<<<<<< HEAD

package com.example.pockethockey;
=======
package com.example.frameworktest;
>>>>>>> f1b5164691a3f1d9c2425b48de5a3f507547a27c

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
<<<<<<< HEAD
    /**
     * @param savedInstanceState current state of the application
     * @post inflates the activity layout
     * @post sets listeners for each button in the layout
=======

    /**
     * This function overrides the AppCompat on create method
     * @param savedInstanceState = saved data
     * @pre This class must be declared as an activity in the manifest
     * @post The xml layout is loaded and button intents are set
>>>>>>> f1b5164691a3f1d9c2425b48de5a3f507547a27c
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //call super
        super.onCreate(savedInstanceState);
        //set content
        setContentView(R.layout.activity_how_to_play);

        // Hide the status bar
        View decorView = getWindow().getDecorView();
<<<<<<< HEAD
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Set listeners for the navigation buttons
=======
        //set full screen
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        //set onclick method for the back button
>>>>>>> f1b5164691a3f1d9c2425b48de5a3f507547a27c
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
