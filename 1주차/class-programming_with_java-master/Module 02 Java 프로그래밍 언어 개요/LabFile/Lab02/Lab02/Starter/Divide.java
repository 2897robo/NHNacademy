import java.util.*;

public class Divide {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            String tmp = sc.nextLine();
            int i = Integer.parseInt(tmp);

            tmp = sc.nextLine();
            int j = Integer.parseInt(tmp);

            sc.close();

            int k = i/j;

            System.out.printf("%d / %d = %d", i,j,k);
        }
        catch(NumberFormatException e) {
            System.out.println("정수를 입력하세요.");
        }
        catch(ArithmeticException e) {
            System.out.println("0을 입력하지 마세요.");
        }
    }
}
