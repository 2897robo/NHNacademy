import java.util.Scanner;

public class Utilities {
    public static int max(int i, int j) {
        if (i > j) {
            return i;
        }
        else {
           return j;
        }
        
        // 아래와 같이도 쓸 수 있습니다.
        //return i > j ? i : j;
    }

    public static void swap(int i, int j) {
        int temp = i;
        i = j;
        j = temp;
    }
}

class Test {
    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        // int i, j;
        // System.out.print("첫 번째 숫자를 입력하세요: ");
        // i = scanner.nextInt();
        // System.out.print("두 번째 숫자를 입력하세요: ");
        // j = scanner.nextInt();
        // int max = Utilities.max(i, j);

        // System.out.printf("%d, %d 중 큰 숫자는 %d입니다.", i, j, max);
        // scanner.close();
        int i = 1, j = 2;
        System.out.printf("swap 메소드 호출 전 i: %d, j: %d \n", i, j);
        Utilities.swap(i, j);
        System.out.printf("swap 메소드 호출 후 i: %d, j: %d", i, j);
    }
}