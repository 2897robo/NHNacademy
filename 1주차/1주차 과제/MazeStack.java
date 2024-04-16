import java.util.*;

public class MazeStack {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) {
        int[][] maze = buildMaze(); 

        boolean pathFound = findPathUsingStack(maze);

        System.out.println("경로찾기를 수행한 미로입니다");
        printMaze(maze);
        System.out.println("경로찾기 실행 결과: " + (pathFound ? "성공" : "실패"));
    }

    // 임의의 크기(8x8)의 미로 생성
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

        System.out.print("임의로 생성한 미로입니다.\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        return maze;
    }

    // Stack 사용한 경로탐색
    private static boolean findPathUsingStack(int[][] maze) {
        int n = maze.length;
        boolean[][] visited = new boolean[n][n];
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, 0}); // 시작점 추가

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int x = current[0];
            int y = current[1];
            visited[x][y] = true; // 현재 위치 방문 표시

            // 현재 위치가 도착점인 경우 탐색 종료
            if (x == n - 1 && y == n - 1) {
                break;
            }

            // 이동 가능한 방향 탐색
            for (int[] dir : DIRECTIONS) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                // 이동 가능한 경우 스택에 추가
                if (isValidMove(maze, visited, newX, newY)) {
                    stack.push(new int[]{newX, newY});
                }
            }
        }

        markPath(maze, visited);
        
        // 도착점에 도착했는지 여부 반환
        return visited[n - 1][n - 1];
    }

    private static boolean isValidMove(int[][] maze, boolean[][] visited, int x, int y) {
        int n = maze.length;
        return x >= 0 && x < n && y >= 0 && y < n && maze[x][y] == 0 && !visited[x][y];
    }

    // 경로 표시
    private static void markPath(int[][] maze, boolean[][] visited) {
        int n = maze.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) {
                    maze[i][j] = 2; // 이동한 경로를 2로 표시
                }
            }
        }
    }

    private static void printMaze(int[][] maze) {
        int n = maze.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
