package com.example.pockethockey;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Puck extends GameObject {

    private int speed;
    private Animation animation = new Animation();
    private Bitmap spritesheet;


    Puck(Bitmap res, int x, int y, int w, int h, int numFrames){
        super.x = x;
        super.y = y;
        width = w;
        height = h;
        speed = 13;

        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;

        for(int i = 0; i < image.length; i++){
            image[i] = Bitmap.createBitmap(spritesheet, 0, i*height, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(120-speed);


    }

    public void update(){
        x += speed - 4;
        animation.update();
    }

    public void draw(Canvas canvas){
        try{
            canvas.drawBitmap(animation.getImage(), x-30, y, null);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
