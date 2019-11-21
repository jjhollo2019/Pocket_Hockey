package com.example.pockethockey;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.HashMap;
import java.util.Map;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    //set the screen dimensions
    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;
    private final float[] accelerometerReading = new float[3];
    private final float[] magnetometerReading = new float[3];


    //local variables and class references for the game
    private Background background;
    private Player player;
    private Map<String, Player> playerMap;
    private Puck puck;
    private long puckStartTime;
    private Goalie goalie;
    private long goalieStartTime;

    private boolean newGame;
    private boolean scored;
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
        playerMap = new HashMap<>();
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
        playerMap.put("right", new Player(BitmapFactory.decodeResource(getResources(), R.drawable.right), 32, 32, 4));
        playerMap.put("left", new Player(BitmapFactory.decodeResource(getResources(), R.drawable.left), 32, 32, 4));
        playerMap.put("upLeft", new Player(BitmapFactory.decodeResource(getResources(), R.drawable.upleft), 32, 32, 4));
        playerMap.put("upRight", new Player(BitmapFactory.decodeResource(getResources(), R.drawable.upright), 32, 32, 4));
        playerMap.put("score", new Player(BitmapFactory.decodeResource(getResources(), R.drawable.score), 32, 32, 4));
        player = playerMap.get("right");

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
            player.setShooting(true);
            if(player.getPlaying()){
                if(!started)started = true;
                player.setPlaying(true);
            }
            return true;
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void update(){
        float[] orientationAngles = Game.orientationAngles;
        background.update(orientationAngles[1], orientationAngles[2]);

        player.setX((int)orientationAngles[1]);
        player.setY((int)orientationAngles[2]);
        System.out.println("Orientation Angles: " + orientationAngles[0] + ", " + orientationAngles[1] + ", " + orientationAngles[2]);
        player.update();
    }

    public boolean collision(GameObject obj1, GameObject obj2){ return Rect.intersects(obj1.getRectangle(), obj2.getRectangle()); }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        final float scaleFactorX = getWidth()/(float)WIDTH;
        final float scaleFactorY = getHeight()/(float)HEIGHT;

        if(canvas != null){
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            background.draw(canvas);

            player.draw(canvas);

            canvas.restoreToCount(savedState);
        }
    }
}