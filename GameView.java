package com.example.frameworktest;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * This class is the controller for the game thread
 * @pre This class must extend the surfaceview class and implement the call back method
 * @post The game thread is started and running
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    //game logic reference
    GameThread gameThread;

    /**
     * This is the constructor for the class
     * @param context = the application context
     * @pre The class must implement the call back method for the surface holder
     * @post Both super method is called and the initView function
     */
    public GameView(Context context) {
        //call super method
        super(context);
        //this function initializes the view and starts the game thread
        initView();
    }

    /**
     * This method overrides the surfaceCreated method from the surfaceview call back method
     * @param surfaceHolder = the surface holder from the class
     * @pre The class must implement the surface holder callback method
     * @post The surface is created
     */
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        //if not running
        if(!gameThread.isRunning()){
            //start a new thread
            gameThread = new GameThread(surfaceHolder);
            gameThread.start();
        //else start the thread
        } else {
            gameThread.start();
        }
    }

    /**
     * This method overrides the surfaceChanged method from the surfaceview call back method and isn't used
     */
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        //do nothing
    }

    /**
     * This method overrides the surfaceDestroyed method from the surfaceview call back method
     * @param surfaceHolder = the surface holder from the class
     * @pre The surfaceCreated method must be called before this method can be used
     * @post The game thread is stopped
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        //if running stop it
        if(gameThread.isRunning()){
            //change status
            gameThread.setIsRunning(false);
            //set condition to keep retrying
            boolean retry = true;
            //while retrying
            while(retry){
                //use a try catch
                try{
                    //join the thread
                    gameThread.join();
                    //break loop
                    retry = false;
                } catch (InterruptedException e){
                    //if there is an error print the stack trace
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * This function will will add the call back to the class and start the game thread
     * @pre The context must be passed to the super class before initializing the thread and adding the call back
     * @post The call back is set to this class and the game thread is initialized
     */
    void initView(){
        //get the holder
        SurfaceHolder holder = getHolder();
        //add callback
        holder.addCallback(this);
        setFocusable(true);
        //create game thread
        gameThread = new GameThread(holder);
    }

    /**
     * This function handles onTouch events for the games
     * @param event = the event caught by the function
     * @return the super for the method
     * @pre The gameEngine must be running before method can be used
     * @post The player velocity is set the VELOCITY_WHEN_JUMPED
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //play the sound
        AppConstants.getSoundBank().playSomeNoise();
        //check for action type
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            //AppConstants.getGameEngine().gameState = 1;
            //if the game isn't over
            if(AppConstants.getGameEngine().gameState != 2) {
                //set player velocity to VELOCITY_WHEN_JUMPED
                AppConstants.getGameEngine().player.setVelocity(AppConstants.VELOCITY_WHEN_JUMPED);
            }
        }
        //return super event
        return super.onTouchEvent(event);
    }
}
