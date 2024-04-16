import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Set<Integer> cardSet = new HashSet<>(); // HashSet을 사용해 중복 검사 시간을 단축
        for(int i=0; i<n; i++) {
            cardSet.add(sc.nextInt()); // 바로 HashSet에 추가
        }

        int m = sc.nextInt();
        int[] arr = new int[m];
        for(int i=0; i<m; i++) {
            arr[i] = sc.nextInt();
        }
        sc.close();

        for(int i=0; i<m; i++) {
            // HashSet의 contains 메소드를 사용해 해당 요소가 있는지 확인
            if(cardSet.contains(arr[i])) {
                System.out.print(1 + " ");
            } else {
                System.out.print(0 + " ");
            }
        }
    }
}
