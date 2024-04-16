import javax.swing.*;
import java.awt.*;

public class TriangleFractal extends JPanel {

    // paintComponent 메서드는 화면에 그릴 내용을 지정합니다.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 첫 번째로 그릴 삼각형의 꼭짓점 좌표를 지정하고, drawTriangle 메서드를 호출합니다.
        drawTriangle(g, 0, getHeight(), getWidth(), getHeight(), getWidth()/2, getHeight()/2);
    }    

    // drawTriangle 메서드는 주어진 꼭짓점 좌표를 가지는 삼각형을 그립니다.
    public void drawTriangle(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3) {
        // 삼각형의 가로 길이가 5 미만이면 더 이상 그리지 않습니다. (재귀 호출 종료 조건)
        if(Math.abs(x2-x1) < 5) return;

        // 세 꼭짓점을 이어 삼각형을 그립니다.
        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x1, y1, x3, y3);
        g.drawLine(x2, y2, x3, y3);

        // 삼각형의 각 변의 중점을 계산하고, 그 중점을 이용해 새로운 삼각형을 그립니다.
        // 이 과정을 재귀적으로 반복합니다.
        drawTriangle(g, x1, y1, (x1+x2)/2, (y1+y2)/2, (x1+x3)/2, (y1+y3)/2);
        drawTriangle(g, (x1+x2)/2, (y1+y2)/2, x2, y2, (x2+x3)/2, (y2+y3)/2);
        drawTriangle(g, (x1+x3)/2, (y1+y3)/2, (x2+x3)/2, (y2+y3)/2, x3, y3);
    }

    // 메인 메서드에서는 JFrame을 생성하고 설정한 후, TriangleFractal 패널을 추가합니다.
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(new TriangleFractal());
        frame.setVisible(true);
    }
}
