import javax.swing.*;
import java.awt.*;

// JFrame을 상속받는 FractalTree 클래스 생성
public class FractalTree extends JFrame {

    // JPanel을 상속받는 내부 클래스 DrawingPanel 생성
    class DrawingPanel extends JPanel{
        // JPanel의 paintComponent 메소드를 오버라이드
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g); // 부모 클래스의 paintComponent 호출
            drawTree(g, 600, 800, -90, 10); // 트리를 그리는 메소드 호출
        }
        
        // 트리를 그리는 메소드
        private void drawTree(Graphics g, int x1, int y1, double angle, int depth) {
            if (depth == 0) return; // 재귀 종료 조건, depth가 0이면 메소드 종료
            int x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * depth * 10.0); // 끝점의 x좌표 계산
            int y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * depth * 10.0); // 끝점의 y좌표 계산
            g.drawLine(x1, y1, x2, y2); // 시작점에서 끝점까지 선 그리기
            drawTree(g, x2, y2, angle - 30, depth - 1); // 왼쪽 가지를 그리기 위한 재귀 호출
            drawTree(g, x2, y2, angle + 30, depth - 1); // 오른쪽 가지를 그리기 위한 재귀 호출
        }
    }

    // FractalTree 클래스의 생성자
    public FractalTree() {
        setBounds(0, 0, 1200, 800); // 프레임의 위치와 크기 설정
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 프레임 닫기 버튼 동작 설정
        add(new DrawingPanel()); // 프레임에 DrawingPanel 객체 추가
    }

    // 메인 메소드
    public static void main(String[] args) {
        new FractalTree().setVisible(true); // FractalTree 객체 생성 후 화면에 보이게 설정
    }
}
