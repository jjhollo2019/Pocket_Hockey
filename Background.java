package com.example.pockethockey;

/**
 * This class handles the parallax scrolling for the background
 * @pre The gameThread && gameView must be intialized before using this class
 * @post The background image is displayed
 */
public class Background {

    //class variables
    private int backgroundImageX, backgroundImageY, backgroundImageVelocity;

    /**
     * This function is the class constructor
     * @pre The gameThread must be running before use
     * @post backgroundImageX = 0 && backgroundImageY = 0 && backgroundImageVelocity = 3;
     */
    public Background(){
        //init class variables
        backgroundImageX = 0;
        backgroundImageY = 0;
        backgroundImageVelocity = 3;
    }

    /**
     * This function returns the X value of the image
     * @return backgroundImageX
     * @pre The class constructor must be called first
     * @post The value of backgroundImageX is not changed
     */
    public int getX() {
        return backgroundImageX;
    }

    /**
     * This function returns the Y value of the image
     * @return backgroundImageY
     * @pre The class constructor must be called first
     * @post The value of backgroundImageY is not changed
     */
    public int getY() {
        return backgroundImageY;
    }

    /**
     * This function sets the value backgroundImageX
     * @param backgroundImageX = the new value of backgroundImageX
     * @pre The class constructor must be called first
     * @post this.backgroundImageX = backgroundImageX
     */
    public void setX(int backgroundImageX) {
        this.backgroundImageX = backgroundImageX;
    }

    /**
     * This function sets the value backgroundImageY
     * @param backgroundImageY = the new value of backgroundImageY
     * @pre The class constructor must be called first
     * @post this.backgroundImageY = backgroundImageY
     */
    public void setY(int backgroundImageY) {
        this.backgroundImageY = backgroundImageY;
    }

    /**
     * This function returns the velocity value of the image
     * @return backgroundImageVelocity
     * @pre The class constructor must be called first
     * @post The value of backgroundImageVelocity is not changed
     */
    public int getVelocity() {
        return backgroundImageVelocity;
    }


}
