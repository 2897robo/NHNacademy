import java.util.Scanner;

public class Divide {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter first Integer:");
            String temp = scanner.nextLine();
            int i = Integer.parseInt(temp);

            System.out.println("Enter second Integer:");
            temp = scanner.nextLine();
            int j = Integer.parseInt(temp);

            scanner.close();

            int k = i / j;

            System.out.printf("%d / %d = %d ", i, j, k);
        }
        catch (Exception e) {
            System.out.println("An Exception was thrown: " + e);
        }
    }
}
