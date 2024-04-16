package com.grapics;

import java.awt.*;

public class FrameDemo01 extends Frame {
    int width = 100;
    int height = 100;

    public FrameDemo01() {
        super("GraphicsDemo02"); // Frame 제목 설정
        setSize(300, 300); // Frame의 크기를 지정한다
        setVisible(true); // Frame을 보여 준다
    }

    public void paint(Graphics g) {
        // 테두리 영역을 얻어 온다
        Insets insets = getInsets();
        // 그림을 그릴 수 있는 영역을 계산한다.
        int drawableWidth = getWidth() - insets.left - insets.right;
        int drawableHeight = getHeight() - insets.top - insets.bottom;
        // 중심을 기준으로 사각형의 위치를 계산한다.
        int x = (drawableWidth - width) / 2;
        int y = (drawableHeight - height) / 2;
        // 사각형 그리기
        g.drawRect(x, y, width, height);
    }

    public static void main(String[] args) throws Exception {
        new FrameDemo01();
    }
}
