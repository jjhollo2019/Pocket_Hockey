package com.example.pockethockey;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {

    private Bitmap image;
    private int x;
    private int y;

    public Background(Bitmap res){
        image = res;
    }

    public void update(float x, float y){
        if(x > 0){
            this.x += 1.5f;
        }
        if(x < 0){
            this.x -= 1.5f;
        }
        if(y > 0){
            this.y += 1.5f;
        }
        if(y < 0){
            this.y -= 1.5f;
        } else {
            this.x = (int) x;
            this.y = (int) y;
        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
    }
}
