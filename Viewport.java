package com.example.pockethockey;

import android.graphics.PointF;
import android.graphics.RectF;

public class Viewport {

    private PointF currentViewCenter;
    private RectF convertedRect;
    private PointF convertedPoint;
    private int pixelsPerMeterX;
    private int pixelsPerMeterY;
    private int screenCenterX;
    private int screenCenterY;
    private int metersToShowX;
    private int metersToShowY;

    // All the rest of the Viewport code goes here

    Viewport(int screenXResolution, int screenYResolution) {

        screenCenterX = screenXResolution / 2;
        screenCenterX = screenYResolution / 2;

        pixelsPerMeterX = screenXResolution / 90;
        pixelsPerMeterY = screenYResolution / 55;

        metersToShowX = 92;
        metersToShowY = 57;

        convertedRect = new RectF();
        convertedPoint = new PointF();

        currentViewCenter = new PointF();

        // End of viewport class
    }

    void setWorldCenter(float x, float y){
        currentViewCenter.x  = x;
        currentViewCenter.y  = y;
    }
}