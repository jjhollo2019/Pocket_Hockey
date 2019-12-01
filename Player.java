/* Jeremy Holloway (jjhollo@clemson.edu, C20581376)
 * Zachary Amend (zamend@clemson.edu, C16422178)
 * CPSC-4150-001
 * 12/2/2019
 * Flight Training
 */
package com.example.flight_training;

/**
 * This class handles the game logic for the game player
 * @pre The gameThread and gameView must be instantiated before using this class
 * @post The player methods are available for use
 */
public class Player {

    //player variables
    private int playerX, playerY, currentFrame, velocity;
    public static int maxFrame;

    /**
     * This function is the class constructor for player
     * @pre gameThread != null && gameView != null
     * @post player X and Y is set to screen center && other variables are initialized
     */
    public Player(){
        playerX = AppConstants.SCREEN_WIDTH/2 - AppConstants.getBitmapBank().getPlayerWidth()/2;
        playerY = AppConstants.SCREEN_HEIGHT/2 - AppConstants.getBitmapBank().getPlayerHeight()/2;
        currentFrame = 0;
        maxFrame = 1;
        velocity = 0;
    }

    /**
     * This function is the getter for velocity
     * @return is the class velocity
     * @pre velocity != null
     * @post the value of velocity is not changed
     */
    public int getVelocity() {
        return velocity;
    }

    /**
     * This function will set the class velocity
     * @param velocity = the new velocity to be set
     * @pre velocity != null
     * @post this.velocity = velocity
     */
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    /**
     * This function is the getter for the current frame
     * @return is the class current frame
     * @pre currentFrame != null
     * @post the value of currentFrame is not changed
     */
    public int getCurrentFrame() {
        return currentFrame;
    }

    /**
     * This function will set the class currentFrame
     * @param currentFrame = the new currentFrame to be set
     * @pre currentFrame != null
     * @post this.currentFrame = currentFrame
     */
    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    /**
     * This function is the getter for playerX
     * @return is the class playerX
     * @pre playerX != null
     * @post the value of playerX is not changed
     */
    public int getX() {
        return playerX;
    }

    /**
     * This function is the getter for playerY
     * @return is the class playerY
     * @pre playerY != null
     * @post the value of playerY is not changed
     */
    public int getY() {
        return playerY;
    }

    /**
     * This function will set the class playerX
     * @param playerX = the new playerX to be set
     * @pre playerX != null
     * @post this.playerX = playerX
     */
    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    /**
     * This function will set the class playerX
     * @param playerY = the new playerY to be set
     * @pre playerY != null
     * @post this.playerY = playerY
     */
    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }
}
