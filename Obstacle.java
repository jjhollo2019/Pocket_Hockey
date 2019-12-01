/* Jeremy Holloway (jjhollo@clemson.edu, C20581376)
 * Zachary Amend (zamend@clemson.edu, C16422178)
 * CPSC-4150-001
 * 12/2/2019
 * Flight Training
 */
package com.example.flight_training;

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
     * @pre gameView != null && gameThread != null
     * @post X and Y values are initialized && random is initialized
     */
    public Obstacle(int obstacleX, int topObstacleOffsetY){
        this.obstacleX = obstacleX;
        this.topObstacleOffsetY = topObstacleOffsetY;
        random = new Random();
    }

    /**
     * This function will get the obstacleOffsetY
     * @return topObstacleOffsetY
     * @pre topObstacleOffset != null
     * @post the value of topObstacleOffsetY is unchanged
     */
    public int getTopObstacleOffsetY() {
        return topObstacleOffsetY;
    }

    /**
     * This function will get the obstacleOffsetY
     * @return topObstacleOffsetY
     * @pre topObstacleOffset != null
     * @post the value of topObstacleOffsetY is unchanged
     */
    public int getObstacleX() {
        return obstacleX;
    }

    /**
     * This function will get the obstacleOffsetY
     * @return topObstacleOffsetY
     * @pre topObstacleOffsetY != null
     * @post the value of topObstacleOffsetY is unchanged
     */
    public int getObstacleY(){
        return topObstacleOffsetY - AppConstants.getBitmapBank().getObstacleHeight();
    }

    /**
     * This function will get the obstacleOffsetY
     * @return topObstacleOffsetX
     * @pre topObstacleOffsetX != null
     * @post the value of topObstacleOffsetX is unchanged
     */
    public int getBottomObstacleY(){
        return topObstacleOffsetY + AppConstants.OBSTACLE_GAP;
    }

    /**
     * This function will set the value of topObstacleX
     * @param obstacleX = the new value for obstacleX
     * @pre topObstacleX != null
     * @post this.topObstacleX = obstacleX
     */
    public void setObstacleX(int obstacleX) {
        this.obstacleX = obstacleX;
    }

    /**
     * This function will set the value of topObstacleOffsetY
     * @param topObstacleOffsetY = the new value for topObstacleOffsetY
     * @pre topObstacleOffsetY != null
     * @post this.topObstacleOffsetY = topObstacleOffsetY
     */
    public void setTopObstacleOffsetY(int topObstacleOffsetY) {
        this.topObstacleOffsetY = topObstacleOffsetY;
    }
}
