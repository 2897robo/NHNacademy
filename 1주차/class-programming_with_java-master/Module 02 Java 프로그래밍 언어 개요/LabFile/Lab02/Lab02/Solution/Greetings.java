import java.util.Scanner;

public class Greetings {
    public static void main(String[] args) {
        String myName;

        System.out.println("Enter your Name");
        Scanner scanner = new Scanner(System.in);
        myName = scanner.next();
        System.out.printf("Greetings, %s", myName);
        scanner.close();
    }
}