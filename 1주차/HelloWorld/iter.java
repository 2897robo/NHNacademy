import java.util.*;

public class iter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int i = 1;
        int j = 1;

        
        while(i<10) {
            System.out.printf("%d 단 구구단", i);
            System.out.println();

            while(j<10) {
                System.out.printf("%d x %d = %d //", i,j, i*j);
                System.out.println();
                j++;
            }

            System.out.println();
            System.out.println();
            j=1;
            i++;
        }


        for(int a=1;a<10;a++) {
            System.out.printf("%d 단", a);
            System.out.println();
            for(int b=1;b<10;b++) {
                System.out.printf("%d x %d = %d //", a, b, a*b);
                System.out.println();
            }

            System.out.println();
            System.out.println();
        }

        sc.close();
    }
}
