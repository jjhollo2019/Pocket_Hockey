package com.example.frameworktest;

import java.util.Random;

/**
 * This class handles the game logic for the obstacle class
 * @pre gameThread != null && gameView != null
 * @post obstacle methods are available
 */
public class Obstacle {

    //class variables
    private int obstacleX, topObstacleOffsetY;
    private Random random;

    /**
     * This is the class constructor for the class
     * @param obstacleX = the x coordinate for the obstacle
     * @param topObstacleOffsetY = the offset for the obstacle y value
     * @pre
     */
    public Obstacle(int obstacleX, int topObstacleOffsetY){
        this.obstacleX = obstacleX;
        this.topObstacleOffsetY = topObstacleOffsetY;
        random = new Random();
    }

    /**
     *
     * @return
     */
    public int getTopObstacleOffsetY() {
        return topObstacleOffsetY;
    }

    /**
     *
     * @return
     */
    public int getObstacleX() {
        return obstacleX;
    }

    /**
     *
     * @return
     */
    public int getObstacleY(){
        return topObstacleOffsetY - AppConstants.getBitmapBank().getObstacleHeight();
    }

    /**
     *
     * @return
     */
    public int getBottomObstacleY(){
        return topObstacleOffsetY + AppConstants.OBSTACLE_GAP;
    }

    /**
     *
     * @param obstacleX
     */
    public void setObstacleX(int obstacleX) {
        this.obstacleX = obstacleX;
    }

    /**
     *
     * @param topObstacleOffsetY
     */
    public void setTopObstacleOffsetY(int topObstacleOffsetY) {
        this.topObstacleOffsetY = topObstacleOffsetY;
    }
}
