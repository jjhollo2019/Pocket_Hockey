/* Jeremy Holloway (jjhollo@clemson.edu, C20581376)
 * Zachary Amend (zamend@clemson.edu, C16422178)
 * CPSC-4150-001
 * 12/2/2019
 * Flight Training
 */
package com.example.flight_training;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class handles the main logic of the game
 * @pre gameView and gameThread must be initialized before using this class
 * @post the game logic is executed
 */
public class GameEngine {

    //background refernce
    private Background background;
    //player reference
    Player player;
    //game state is a static member
    static int gameState;
    //list of obstacles
    private ArrayList<Obstacle> obstacles;
    //random generator
    Random random;
    private int score;
    //scoring obstacle tracker
    private int scoringObstacle;
    //paint variable for scoring
    Paint scorePaint;

    /**
     * This function is the class constructor
     * @pre The gameView and gameThread must be initialized before calling this constructor
     * @post all class variables are initialized
     */
    public GameEngine(){
        //create new background and player
        background = new Background();
        player = new Player();

        //set the game state
        // 0 = not started
        // 1 = playing
        // 2 == game over
        gameState = 1;

        //init the obstacle list
        obstacles = new ArrayList<>();
        //init random generator
        random = new Random();
        //create obstacles
        for(int i = 0; i < AppConstants.numberOfObstacles; i++){
            //set an X value
            int obsX = AppConstants.SCREEN_WIDTH + i * AppConstants.distanceBetweenObstacles;
            //create seed for random generator
            int seed = AppConstants.maxObstacleOffsetY - AppConstants.minObstacleOffsetY + 1;
            //check to be sure bound isn't negative
            if(seed < 0){
                seed *= -1;
            }
            //calc the offset
            int topObsOffsetY = AppConstants.minObstacleOffsetY + random.nextInt(seed);
            //create the object
            Obstacle obstacle = new Obstacle(obsX, topObsOffsetY);
            //add to list
            obstacles.add(obstacle);
        }
        //set score
        score = 0;
        //set scoring obstacle
        scoringObstacle = 0;
        //create scoring paint
        scorePaint = new Paint();
        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(100);
        scorePaint.setTextAlign(Paint.Align.LEFT);
    }

    /**
     * This function is for updating and drawing the background
     * @param canvas = the canvas to draw and update
     * @pre background != null
     * @post background is updated and redrawn on the canvas
     */
    public void updateAndDrawBackgroundImage(Canvas canvas){
        //get new position
        background.setX(background.getX() - background.getVelocity());
        //if the background needs to be reset
        if(background.getX() < -AppConstants.getBitmapBank().getBackgroundWidth()){
            background.setX(0);
        }
        //draw updated background
        canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), background.getX(), background.getY(), null);
        //if too far left redraw the image
        if(background.getX() < -(AppConstants.getBitmapBank().getBackgroundWidth() - AppConstants.SCREEN_WIDTH)){
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), background.getX()+AppConstants.getBitmapBank().getBackgroundWidth(), background.getY(), null);
        }
    }

    /**
     * This function will update and draw the player
     * @param canvas = the canvas to draw and update
     * @pre player != null
     * @post player is updated and redrawn on the canvas
     */
    public void updateAndDrawPlayer(Canvas canvas){
        //if the game is still active
        if(gameState == 1) {
            //if the player is on screen
            if (player.getY() < AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getPlayerHeight() || player.getVelocity() < 0) {
                //update positional data
                System.out.println(Game.phoneAngle);
                if(Game.phoneAngle > 9.7f){
                    //move the player down
                    player.setVelocity(player.getVelocity() - 1);
                    player.setPlayerY(player.getY() + player.getVelocity());
                }
                else if(Game.phoneAngle < 9.1f) {
                    //set player going up
                    player.setVelocity(player.getVelocity() + 1);
                    player.setPlayerY(player.getY() + player.getVelocity());
                } else {
                    //else have the player continue forward
                    player.setPlayerY(player.getY());
                }
            }
        }
        //get the current frame
        int currentFrame = player.getCurrentFrame();
        //draw the frame
        canvas.drawBitmap(AppConstants.getBitmapBank().getPlayer(currentFrame), player.getX(), player.getY(), null);
        //increment the frame for the next call to draw
        currentFrame++;
        //check if frame needs to be reset
        if(currentFrame > player.maxFrame){
            currentFrame = 0;
        }
        //set the new frame
        player.setCurrentFrame(currentFrame);
    }

    /**
     * This function will update and draw the level obstacles
     * @param canvas = the canvas to draw and update
     * @pre obstacles != null && obstacles.size() < 1
     * @post obstacle positions are updated and redrawn
     */
    public void updateAndDrawObstacles(Canvas canvas){
        //if the game is still being played
        if(gameState == 1){
            AppConstants.getSoundBank().playPlane();
            //check for collision with obstacles
            if((obstacles.get(scoringObstacle).getObstacleX() < player.getX() + AppConstants.getBitmapBank().getPlayerWidth()) && (obstacles.get(scoringObstacle).getTopObstacleOffsetY() > player.getY() || obstacles.get(scoringObstacle).getBottomObstacleY() < (player.getY() + AppConstants.getBitmapBank().getPlayerHeight()))){
                //stop the game
                gameState = 2;
                AppConstants.getSoundBank().stopPlane();
                //decrease the score
                Intent goToSplash = new Intent(AppConstants.gameActivityContext, Splash.class);
                goToSplash.putExtra("level", AppConstants.level);
                goToSplash.putExtra("strikes", ++AppConstants.strikes);
                AppConstants.gameActivityContext.startActivity(goToSplash);
            }
            //check if player missed object
            else if(obstacles.get(scoringObstacle).getObstacleX() < player.getX() - AppConstants.getBitmapBank().getObstacleWidth()){
                //increase score
                score++;
                if(score == AppConstants.level){
                    AppConstants.getSoundBank().stopPlane();
                    Intent goToSplash = new Intent(AppConstants.gameActivityContext, Splash.class);
                    goToSplash.putExtra("level", ++AppConstants.level);
                    goToSplash.putExtra("strikes", AppConstants.strikes);
                    AppConstants.gameActivityContext.startActivity(goToSplash);
                }
                //change obstacle
                scoringObstacle++;
                //reset obstacle if needed
                if(scoringObstacle > AppConstants.numberOfObstacles - 1){
                    scoringObstacle = 0;
                }
            }
            //update the position of all the game obstacles
            for(int i = 0; i < AppConstants.numberOfObstacles; i++){
                //check if on screen
                if(obstacles.get(i).getObstacleX() < -AppConstants.getBitmapBank().getObstacleWidth()){
                    //update positional data
                    obstacles.get(i).setObstacleX(obstacles.get(i).getObstacleX() + AppConstants.numberOfObstacles * AppConstants.distanceBetweenObstacles);
                    int seed = AppConstants.maxObstacleOffsetY - AppConstants.minObstacleOffsetY + 1;
                    if(seed <= 0){
                        seed *= -1;
                    }
                    int topObsOffsetY = AppConstants.minObstacleOffsetY + random.nextInt(seed);
                    obstacles.get(i).setTopObstacleOffsetY(topObsOffsetY);
                }
                //set new position and redraw the obstacles at their new position
                obstacles.get(i).setObstacleX(obstacles.get(i).getObstacleX() - AppConstants.obstacleVelocity);
                canvas.drawBitmap(AppConstants.getBitmapBank().getObstacle_top(), obstacles.get(i).getObstacleX(), obstacles.get(i).getObstacleY(), null);
                canvas.drawBitmap(AppConstants.getBitmapBank().getObstacle_bottom(), obstacles.get(i).getObstacleX(), obstacles.get(i).getBottomObstacleY(), null);
            }
        }
    }
}
