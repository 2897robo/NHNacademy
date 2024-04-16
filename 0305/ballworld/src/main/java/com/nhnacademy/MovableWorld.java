package com.nhnacademy;

public class MovableWorld extends World {
    int moveCount=0;  //실행하는 동안 이동 횟수
    int maxMoveCount=0;   //최대 이동 횟수
    int dt;

    public void setDt(int dt) {
        this.dt = dt;
    }

    public int getDt() {
        return dt;
    }

    //이동 횟수(moveCount) 초기화
    public void reset() {
        this.moveCount = 0;
    }

    //단위 시간 단위 이동
    //호출 시 등록된 볼 중에서 이동할 수 있는 MovableBall만 1회 이동
    //이동 후 화면 다시 그리기
    //AWT에서는 다시 그리기 위한 repaint 함수 지원
    //이동 횟수 저장
    //최대 이동 횟수를 넘지는 않음
    public void move() {
        if( (getMaxMoveCount()==0) || (getMovementCount() < getMaxMoveCount()) ) {
            for(int i=0; i<getCount(); i++) {
                Ball ball = get(i);
                if(ball instanceof MovableBall) {
                    ((MovableBall)ball).move();
                }
            }
            moveCount++;
            repaint();
        }
    }

    //지정한 횟수 동안 ball 이동
    //최대 이동 횟수가 0이면, 계속 이동
    public void run() throws InterruptedException {
        while (true) {
            move();
            Thread.sleep(100);
        }
    }

    //이동 횟수 반환
    public int getMovementCount() {
        return moveCount;
    }

    //최대 이동 횟수 반환
    public int getMaxMoveCount() {
        return maxMoveCount;
    }

    //최대 이동 횟수 설정
    public void setMaxMoveCount(int count) {
        if(count < 0) {
            throw new IllegalArgumentException();
        }

        this.maxMoveCount = count;
    }
}
