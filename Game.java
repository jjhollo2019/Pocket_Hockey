package com.example.pockethockey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class Game extends AppCompatActivity implements SensorEventListener {
    public static int strikes;     // 3 strikes means GAME OVER
    public static int level;     // Start at Level 1
    private static boolean isGameOver;
    private static boolean highScoreSet;
    private static HighScoreDatabase database;
    private static FragmentManager fragmentManager;

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

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        AppConstants.gameActivityContext = this;

        AppConstants.initialization(this.getApplicationContext());

        setContentView(new GameView(this));
        game = new FrameLayout(this);
        database = new HighScoreDatabase(getApplicationContext());

        highScoreSet = false;
        isGameOver = false;

        // Grab strike and level data from Splash.java
        Intent fromSplash = getIntent();
        level = fromSplash.getIntExtra("level", 1);
        strikes = fromSplash.getIntExtra("strikes", 0);
        AppConstants.level = level;
        AppConstants.strikes = strikes;

        // TODO: Investigate NullPointerException
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        fragmentManager = getSupportFragmentManager();
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
        phoneAngle = sensorEvent.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i){
    }
}
