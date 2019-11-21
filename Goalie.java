package com.example.pockethockey;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class Goalie extends GameObject {

    private int score;
    private int speed;
    private Random rand = new Random();
    private Animation animation = new Animation();
    private Bitmap spritesheet;

    public Goalie(Bitmap res, int x, int y, int w, int h, int s, int numFrames){

        super.x = x;
        super.y = y;
        width = w;
        height = h;
        score = s;

        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;

        for(int i = 0; i < image.length; i++){
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }

        animation.setFrames(image);

        animation.setDelay(100 - speed);
    }

    public void update(){
        animation.update();
    }

    public void draw(Canvas canvas){

        try{
            canvas.drawBitmap(animation.getImage(), x, y, null);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getWidth(){return width-10;}
}
