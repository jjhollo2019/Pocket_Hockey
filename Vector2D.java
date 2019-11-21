package com.example.pockethockey;

public class Vector2D {
    public float x, y;

    public Vector2D(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void add(Vector2D other){
        this.x += other.x;
        this.y += other.y;
    }

    public Vector2D scale(float scalar){
        x *= scalar;
        y *= scalar;
        return this;
    }

    public float getMagnitude(){
        return (float) Math.sqrt((x*x) + (y * y));
    }
}
