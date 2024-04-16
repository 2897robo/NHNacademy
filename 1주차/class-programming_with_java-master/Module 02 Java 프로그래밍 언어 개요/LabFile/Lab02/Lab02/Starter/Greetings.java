import java.util.Scanner;

public class Greetings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String myName;
        
        System.out.print("사용자의 이름을 입력하세요 : ");
        myName = sc.nextLine();

        System.out.printf("Greetings, %s", myName);
        sc.close();
    }
}