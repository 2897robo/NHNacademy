import java.util.*;

public class exception {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            int i = sc.nextInt();
            int j = 5/i;
            System.out.println(j);
        }
        catch(Exception e) {
            System.out.println("0으로 나눌수 없음");
        }
        finally {
            sc.close();
        }

        
    }
}
