/* Jeremy Holloway (jjhollo@clemson.edu, C20581376)
 * Zachary Amend (zamend@clemson.edu, C16422178)
 * CPSC-4150-001
 * 12/2/2019
 * Flight Training
 */
package com.example.flight_training;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

/**
 * This class handles hardware responses while the game is running in the content view
 * @pre This class must be called to start the game
 * @post The game will be running on the screen
 */
public class Game extends AppCompatActivity implements SensorEventListener {
    public static int strikes;     // 3 strikes means GAME OVER
    public static int level;     // Start at Level 1
    // Accelerometer data
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    public static float phoneAngle;

    // Jeremy's variables
    FrameLayout game;

    /**
     * @param savedInstanceState current state of the application
     * @post inflates the activity layout
     * @post initializes private data for new level
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set full screen options
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //set game context
        AppConstants.gameActivityContext = this;

        //init static variables
        AppConstants.initialization(this.getApplicationContext());

        //start the game view
        setContentView(new GameView(this));
        //set layout style
        game = new FrameLayout(this);

        // Grab strike and level data from Splash.java
        Intent fromSplash = getIntent();
        level = fromSplash.getIntExtra("level", 1);
        strikes = fromSplash.getIntExtra("strikes", 0);
        //set level and strikes
        AppConstants.level = level;
        AppConstants.strikes = strikes;

        //get the accelerometer
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    /**
     * @post registers listener for accelerometer readings
     */
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * @post unregisters listener for accelerometer readings
     */
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this, mAccelerometer);
    }

    /**
     * @param sensorEvent containing accelerometer values
     * @post stores accelerometer values in private data
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        /*
         * To display accelerometer values:
         * X = sensorEvent.values[0]
         * Y = sensorEvent.values[1] -- this is primarily what we're concerned with
         * Z = sensorEvent.values[2]
         */
        phoneAngle = sensorEvent.values[0];
    }

    /**
     * @param sensor = sensor to be changed
     * @param i = changed value
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i){
    }
}
