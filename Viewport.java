package com.example.pockethockey;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Viewport extends SurfaceView implements SurfaceHolder.Callback {

    private static GamePanel gamePanel;
    private static PointF currentViewCenter;
    private RectF convertedRect;
    private PointF convertedPoint;
    private int areaPixelsX;
    private int areaPixelsY;
    private int screenCenterX;
    private int screenCenterY;
    private int areaX;
    private int areaY;
    // All the rest of the Viewport code goes here

    Viewport(int XResolution, int YResolution, Context context) {
        super(context);

        screenCenterX = XResolution / 2;
        screenCenterX = YResolution / 2;

        areaPixelsX = XResolution / 90;
        areaPixelsY = YResolution / 55;

        areaX = 92;
        areaY = 57;

        convertedRect = new RectF();
        convertedPoint = new PointF();

        currentViewCenter = new PointF();
    }

    public void update(float x, float y){
        currentViewCenter.x  = x;
        currentViewCenter.y  = y;
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }
}