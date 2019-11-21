package com.example.pockethockey;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;

    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run(){
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;

        int frameCount = 0;
        long targetTime = 1000/FPS;

        while (running){
            startTime = System.nanoTime();

            canvas = null;
            try{
                canvas = surfaceHolder.lockCanvas();
                //updating and drawing on the canvas must be a synchronized action
                synchronized (surfaceHolder){
                    gamePanel.update();
                    gamePanel.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try{
                    surfaceHolder.unlockCanvasAndPost(canvas);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //convert to milliseconds
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            //time needed to catch up
            waitTime = targetTime-timeMillis;
            if(waitTime < 0) waitTime = 0;

            try{
                //sleep for the calculated time
                sleep(waitTime);
            } catch (Exception e){
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if(frameCount == FPS){
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println("Average FPS: " + averageFPS);
            }


        }
    }

    public void setRunning(boolean b){ running = b; }
}
