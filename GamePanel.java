package com.example.pockethockey;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    //thread reference
    private MainThread thread;

    public GamePanel(Context context){
        super(context);
        //event interceptor
        getHolder().addCallback(this);

        //set the game logic
        thread = new MainThread(getHolder(), this);

        //focus on the event
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;

        while(retry){
            try{
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        return super.onTouchEvent(event);
    }

    public void update(){
        //
    }
}
