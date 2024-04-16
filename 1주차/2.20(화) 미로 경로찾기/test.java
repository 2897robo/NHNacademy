/*
 * 정방형 2차원 배열 0,1 로 만들어 시작부터 출구까지 길을 찾으면 길을 찾았다. 아니면 길이 없다.
 * 배열 값은 int, 0이면 갈수 있는 길, 1이면 못가는 길
 * 경로 찾기 알고리즘 구현하기. 
 */

import java.util.*;

public class test {
    // 이동 방향 정의 (우, 하, 좌, 상)
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int[][] buildMaze() {
        int n = 0;
        Random random = new Random();

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("정방형 배열 크기를 입력하세요: ");
            n = sc.nextInt(); // 미로 크기 입력받기
            System.out.println();
        } catch (InputMismatchException e) {
            System.out.println("숫자만 입력해주세요."); // 미로 크기인 n에 문자값이 입력되었을 경우 프로그램 종료
            System.exit(1);
        }

        int[][] maze = new int[n][n]; // 정방형 n*n 배열 선언하기

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maze[i][j] = random.nextInt(2); // maze 배열에 0 또는 1 값 랜덤 설정
                if ((i == 0 && j == 0) || (i == n - 1 && j == n - 1)) {
                    maze[i][j] = 0;
                }
            }
        }

        System.out.println("임의로 생성한 미로입니다.\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        return maze;
    }

    // 주어진 배열에서 경로를 찾는 함수
    private static boolean findPath(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n]; // 방문 여부를 나타내는 배열
        Stack<int[]> stack = new Stack<>(); // DFS를 위한 스택

        // 시작점에서 출발
        stack.push(new int[]{0, 0});

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int x = current[0];
            int y = current[1];

            // 도착점에 도달했을 경우
            if (x == n - 1 && y == n - 1)
                return true;

            // 현재 위치를 방문했음을 표시
            visited[x][y] = true;

            // 이동 가능한 인접한 칸들을 확인하며 스택에 추가
            for (int[] dir : DIRECTIONS) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                // 배열 범위를 벗어나는 경우 무시
                if (newX < 0 || newX >= n || newY < 0 || newY >= n)
                    continue;

                // 갈 수 있는 길이고 아직 방문하지 않은 경우 스택에 추가
                if (grid[newX][newY] == 0 && !visited[newX][newY])
                    stack.push(new int[]{newX, newY});
            }
        }

        // 도착점에 도달하지 못한 경우
        return false;
    }

    public static void main(String[] args) {
        int[][] maze = buildMaze();
        boolean pathExists = findPath(maze);

        // 결과 출력
        if (pathExists)
            System.out.println("길을 찾았습니다.");
        else
            System.out.println("길이 없습니다.");
    }
}
