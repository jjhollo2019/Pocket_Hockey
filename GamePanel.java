package com.example.pockethockey;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    //set the screen dimensions
    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;
    public static final int MOVESPEED = -5;

    //local variables and class references for the game
    private Background background;
    private Player player;
    private Puck puck;
    private long puckStartTime;
    private Goalie goalie;
    private long goalieStartTime;

    private boolean newGame;
    private long startReset;
    private boolean reset;
    private boolean disappear;
    private boolean started;

    //thread reference
    private MainThread thread;

    /**
     * This function is the contructor for the gamepanel class, which is the main driver for the game
     * @param context = the application context
     */
    public GamePanel(Context context){
        super(context);
        //event interceptor
        getHolder().addCallback(this);

        //set the game logic
        thread = new MainThread(getHolder(), this);

        //focus on the event
        setFocusable(true);
    }

    /**
     * This function is similar to the onCreate method of most other classes, and
     * @param surfaceHolder
     */
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.bigrink));
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.right), 32, 32, 4);
        //puck = new Puck(BitmapFactory.decodeResource(getResources(), R.drawable.puck), player.getX(), player.getY(), 32, 32, 3);
        puckStartTime = System.nanoTime();
        //goalie = new Goalie(BitmapFactory.decodeResource(getResources(), R.drawable.sprite_11), player.getX()+100, player.getY(), 326, 317, 0, 1);
        goalieStartTime = System.nanoTime();
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;

        //keep trying until thread can be fully joined
        while(retry){
            try{
                thread.setRunning(false);
                thread.join();
                retry = false;
                thread = null;
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        //change this logic for gyroscope
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            /*if(!player.getPlaying() && newGame && reset){
                player.setPlaying(true);
                player.setUp(true);
            }*/
            if(player.getPlaying()){
                if(!started)started = true;
                player.setPlaying(true);
                //reset = false;
               // player.setUp(true);
            }
            return true;
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
            //player.setUp(false);
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void update(){
        //if(player.getPlaying()){
            background.update();
            player.update();
        //}
        /*if(player.getPlaying()) {
            background.update();
            player.update();
            //puck.update();
            //goalie.update();
        } else {
            player.resetDYA();
            if(!reset){
                newGame = false;
                startReset = System.nanoTime();
                reset = true;
                disappear = true;
            }
            long resetElapsed = (System.nanoTime() - startReset) / 1000000;
            if(resetElapsed > 2500 && newGame) {
                newGame();
            }
        }*/
    }

    public boolean collision(GameObject obj1, GameObject obj2){
        if(Rect.intersects(obj1.getRectangle(), obj2.getRectangle())){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        final float scaleFactorX = getWidth()/(float)WIDTH;
        final float scaleFactorY = getHeight()/(float)HEIGHT;

        if(canvas != null){
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            background.draw(canvas);
            //if(!disappear) {
                player.draw(canvas);
            //}
            if(collision(goalie, puck)){
                //add code for scoring
            }
            //puck.draw(canvas);
            //goalie.draw(canvas);

            canvas.restoreToCount(savedState);
        }
    }

    public void newGame(){
        disappear = false;
        player.resetDYA();
        player.resetScore();

        //reset player location
        newGame = true;
    }
}