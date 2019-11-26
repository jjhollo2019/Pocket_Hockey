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
    public static int strikes = 0;     // 3 strikes means GAME OVER
    public static int level = 1;     // Start at Level 1
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

        // TODO: Investigate NullPointerException
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        fragmentManager = getSupportFragmentManager();
    }

//    @Override
//    protected void onStart(){
//        super.onStart();
//        if (isGameOver) onGameEnd();
//    }

    // For the accelerometer readings
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    // For the accelerometer readings
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this, mAccelerometer);
    }

    /**
     *
     * @param sensorEvent containing accelerometer values
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

//    // If player scores
//    public static void onLevelPass(){
//        // Pass data to the splash screen to display the next level
//        Intent goToSplash = new Intent(AppConstants.gameActivityContext, Splash.class);
//        goToSplash.putExtra("level", level++);
//        goToSplash.putExtra("strikes", strikes);
//        AppConstants.gameActivityContext.startActivity(goToSplash);
//    }
//
//    // If player doesn't score (misses shot or doesn't shoot at all)
//    public static void onLevelFail(){
//        strikes++;
//        if (strikes == 3){
//            onGameEnd();
//            return;
//        }
//
//        // Send level and strikes to Splash.java
//        Intent goToSplash = new Intent(AppConstants.gameActivityContext, Splash.class);
//        goToSplash.putExtra("level", level);
//        goToSplash.putExtra("strikes", strikes);
//        AppConstants.gameActivityContext.startActivity(goToSplash);
//    }
//
//    private static void onGameEnd(){
//        isGameOver = true;
//
//        if (!highScoreSet){
//            // If the player got a new highscore, open HighScoreEnding
//            if (database.checkHighScores(AppConstants.gameActivityContext, level)){
//                // Open the NewHighscoreDialog dialog
//                NewHighscoreDialog dialog = new NewHighscoreDialog();
//                // Don't allow players to click outside the dialog box (we don't want it to disappear)
//                dialog.setCancelable(false);
//                dialog.show(fragmentManager, "Game Over (New Highscore!)");
//                highScoreSet = true;
//                return;
//            }
//        }
//        // In the case of no high score, open the GameOver dialog
//        GameOver dialog = new GameOver();
//        // Don't allow players to click outside the dialog box (we don't want it to disappear)
//        dialog.setCancelable(false);
//        dialog.show(fragmentManager, "Game Over (No Highscore)");
//    }
}
