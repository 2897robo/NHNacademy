package com.nhnacademy;

import java.awt.Color;

public class MovableBall extends PaintableBall {
    int dx, dy;

    public MovableBall(int x, int y, int radius, Color color) {
        super(x, y, radius, color);
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void move() {
        moveTo(getX() + getDx(), getY() + getDy());
    }

    public void moveTo(int x, int y) {
        super.setX(x);      //Ball 의 Set 메소드는 디폴트 접근지정자. 상속받은 클래스 외부에선 접근 불가
        super.setY(y);
    }
}