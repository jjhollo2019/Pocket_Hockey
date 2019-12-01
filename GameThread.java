/* Jeremy Holloway (jjhollo@clemson.edu, C20581376)
 * Zachary Amend (zamend@clemson.edu, C16422178)
 * CPSC-4150-001
 * 12/2/2019
 * Flight Training
 */
package com.example.flight_training;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.view.SurfaceHolder;

/**
 * This class runs the game logic on a separate thread from the main activity
 * @pre This class must extend the Thread class to create a thread
 * @post The game is running inside the class thread
 */
public class GameThread extends Thread {

    //class variables
    private SurfaceHolder surfaceHolder;
    private boolean isRunning;
    long startTime, loopTime;
    final long DELAY = 33;

    /**
     * This function sets the class surfaceView holder from the gameView constructor and sets running condition
     * @param holder = the surfaceView holder from the gameView class
     * @pre The class must extend the Thread class
     * @post surfaceView = holder && isRunning = true
     */
    public GameThread(SurfaceHolder holder){
        //set class variables
        surfaceHolder = holder;
        isRunning = true;
    }

    /**
     * This function overrides the run method from the Thread class to run the game logic ina thread
     * @pre isRunning must be set to true for this method to have any effect
     * @post The update and draw methods from the gameEngine class are called
     */
    @Override
    public void run(){
        //loop until the isRunning is false
        while (isRunning){
            //set start time
            startTime = SystemClock.uptimeMillis();
            //lock the canvas
            Canvas canvas = surfaceHolder.lockCanvas(null);
            if(canvas != null){
                //drawing on the canvas must be synchronized for the game to make sense
                synchronized (surfaceHolder){
                    //call the update and draw methods
                    AppConstants.getGameEngine().updateAndDrawBackgroundImage(canvas);
                    AppConstants.getGameEngine().updateAndDrawPlayer(canvas);
                    AppConstants.getGameEngine().updateAndDrawObstacles(canvas);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            //get time it took to loop
            loopTime = SystemClock.uptimeMillis() - startTime;
            //if the looptime is less than the delay
            if(loopTime < DELAY){
                //use a try catch to make the thread sleep
                try {
                    Thread.sleep(DELAY - loopTime);
                } catch (InterruptedException e){
                    //print stack trace
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * This method gets the value of isRunning
     * @return The value of isRunning
     * @pre The class constructor must be called before calling this method
     * @post The value of isRunning is not changed
     */
    public boolean isRunning(){
        return isRunning;
    }

    /**
     * This function will set the value of isRunning
     * @param running = the value os isRunning to be set to
     * @pre The class constructor must be called before using this method
     * @post isRunning = running
     */
    public void setIsRunning(boolean running) {
        isRunning = running;
    }
}
