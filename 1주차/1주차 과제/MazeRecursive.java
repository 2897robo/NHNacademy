import java.util.*;

public class MazeRecursive {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) {
        int[][] maze = buildMaze();

        boolean pathFound = findPathUsingRecursive(maze, 0, 0);

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

    // 재귀를 이용한 경로 탐색
    private static boolean findPathUsingRecursive(int[][] maze, int x, int y) {
        int n = maze.length;
        boolean[][] visited = new boolean[n][n];
        return explore(maze, visited, x, y);
    }

    private static boolean explore(int[][] maze, boolean[][] visited, int x, int y) {
        int n = maze.length;
        if (x < 0 || x >= n || y < 0 || y >= n || maze[x][y] == 1 || visited[x][y])
            return false;

        if (x == n - 1 && y == n - 1)
            return true;

        visited[x][y] = true;

        for (int[] dir : DIRECTIONS) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (explore(maze, visited, newX, newY)) {
                maze[x][y] = 2; // 이동한 경로를 2로 표시
                return true;
            }
        }

        return false;
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
