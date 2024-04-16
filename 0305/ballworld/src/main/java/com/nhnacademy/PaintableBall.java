package com.nhnacademy;

import java.awt.*;

public class PaintableBall extends Ball {
    public static final Color DEFAULT_COLOR = Color.BLACK;
    private Color color;

    public PaintableBall(int x, int y, int radius) {
        this(x, y, radius, DEFAULT_COLOR);
    }

    public PaintableBall(int x, int y, int radius, Color color) {
        super(x, y, radius);

        if(color == null) {
            throw new IllegalArgumentException();
        }
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    /**
     * 
     * @param color
     * @throws IllegalArgumentException color는 null을 허용하지 않습니다.
     */
    public void setColor(Color color) {
        if(color == null) {
            throw new IllegalArgumentException();
        }
        this.color = color;
    }

    public void paint(Graphics g) {
        if(g == null) {
            throw new IllegalArgumentException();
        }

        Color origin = g.getColor();

        g.setColor(getColor());
        g.fillOval(getX(), getY(), getRadius()*2, getRadius()*2);

        g.setColor(origin);
    }
}
