package com.example.pockethockey;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {

    private Bitmap image;
    private int x;
    private int y;
    private int dx;

    public Background(Bitmap res){
        image = res;
        dx = GamePanel.MOVESPEED;
    }

    public void update(){
        x += dx;
        if(x<-GamePanel.WIDTH){
            x = 0;
        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);

        if(x < 0){

        }
    }


}
