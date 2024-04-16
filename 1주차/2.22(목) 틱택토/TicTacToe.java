/*
 * 과제). 익일 11:30까지
자바로 틱택토 구현
내가 입력시 컴퓨터가 자동(랜덤)으로 입력
가로, 세로, 대각선 연결시 우승.
마지막에 누가 승리 했는지 출력

힌트). SOLID 법칙 적용해라. 메소드 하나가 한 가지 일만 하도록 만들기
화면 찍는 메소드 하나
문자열 자르는 메소드 하나
배열 값 찍는 메소드 하나
승리 판단 메소드 하나
-> 함수와 프로시저를 기반으로 메소드를 디바이드&콘커
 */

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    static char[][] board = new char[3][3]; // 게임판을 나타내는 2차원 배열
    static boolean game_over = false;
    static char winner = ' ';

    // 게임판 초기화
    public static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // 게임판 출력
    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // 플레이어 또는 컴퓨터가 놓을 위치를 선택
    public static void selectPosition(char symbol) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        if (symbol == 'X') {
            // 컴퓨터의 차례
            int row, col;
            do {
                row = random.nextInt(3);
                col = random.nextInt(3);
            } while (board[row][col] != ' ');

            board[row][col] = 'X';
            System.out.println("컴퓨터가 (" + (row + 1) + ", " + (col + 1) + ") 위치에 X를 놓았습니다.");
        } else {
            // 플레이어의 차례
            System.out.print("행과 열을 입력하세요 (1~3): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;

            if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
                System.out.println("잘못된 위치입니다. 다시 입력하세요.");
                selectPosition(symbol);
            } else {
                board[row][col] = 'O';
            }
        }
    }

    // 게임 종료 조건 검사
    public static boolean checkGameEnd() {
        // 가로, 세로, 대각선 확인
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                winner = board[i][0];
                game_over = true;
                return true;
            }
            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                winner = board[0][i];
                game_over = true;
                return true;
            }
        }
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            winner = board[0][0];
            game_over = true;
            return true;
        }
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            winner = board[0][2];
            game_over = true;
            return true;
        }

        // 모든 칸이 찼는지 확인
        boolean full = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    full = false;
                    break;
                }
            }
            if (!full) break;
        }
        if (full) {
            game_over = true;
            winner = ' ';
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        initializeBoard();
        printBoard();

        while (!game_over) {
            selectPosition('O');
            printBoard();
            if (checkGameEnd()) break;

            selectPosition('X');
            printBoard();
            if (checkGameEnd()) break;
        }

        if (winner == 'O') {
            System.out.println("사용자가 이겼습니다!");
        } 
        else if (winner == 'X') {
            System.out.println("컴퓨터가 이겼습니다!");
        }

        scanner.close();
    }
}
