package com.nhnacademy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ball {
    private int x;
    private int y;
    private int radius;
    Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

    public Ball(int x, int y, int radius) {
        if(radius <= 0) {
            throw new IllegalArgumentException("반지름은 0보다 커야합니다.");
        }

        if( (x + (long)radius > Integer.MAX_VALUE)
            || (x - (long)radius > Integer.MAX_VALUE)
            || (y + (long)radius > Integer.MAX_VALUE)
            || (y - (long)radius > Integer.MAX_VALUE)) {
                throw new IllegalArgumentException("공이 정수공간을 벗어납니다.");
            }
        this.x = x;
        this.y = y;
        this.radius = radius;

        logger.trace("Ball Created : {}, {}, {}" ,x,y,radius);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "(" + getX() + "," + getY() + "," + getRadius() + ")";
    }
}