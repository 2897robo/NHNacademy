package com.nhnacademy;

import java.util.LinkedList;
import java.util.List;
import java.awt.*;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class World extends JPanel {
    Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

    private List<Ball> ballList = new LinkedList<>();

    @SuppressWarnings("unused")
    public void add(Ball ball) {
        //logger.trace("W:{}, H:{}", getWidth(), getHeight());
        if((ball.getX() - ball.getRadius() < 0) 
            || (ball.getX() + ball.getRadius() > getWidth())
            || (ball.getY() - ball.getRadius() < 0)
            ||  (ball.getY() + ball.getRadius() > getWidth())) {
                throw new IllegalArgumentException();
        }

        for(Ball existBall : ballList) {
            if((Math.sqrt(Math.pow(existBall.getX() - ball.getX(), 2) + (Math.pow(existBall.getY() - ball.getY(), 2)))) < existBall.getRadius()) {
                throw new IllegalArgumentException();
            }
        }

        if(ball == null) {
            throw new IllegalArgumentException();
        }
        
        ballList.add(ball);
    }

    public void remove(Ball ball) {
        ballList.remove(ball);
    }

    @Override
    public void remove(int index) {
        ballList.remove(index);
    }

    public int getCount() {
        return ballList.size();
    }

    public Ball get(int index) {
        return ballList.get(index);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

            // Ball 객체 그리기
        for (Ball ball : ballList) {
            if(ball instanceof PaintableBall) {
                ((PaintableBall)ball).paint(g);
            }
        }
    }
}
