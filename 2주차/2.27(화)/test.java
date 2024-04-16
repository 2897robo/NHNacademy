import java.util.*;

public class test {
    public static void main(String[] args) {
        student s1 = new student(5, "giuk");
        student s2 = new student(2, "hoe");

        switch (s1.compareTo(s2)) {
            case 1:
                System.out.println("s1 이 더큼");
            case 0:
                System.out.println("똑같음");
            case -1:
                System.out.println("s2 가 더큼");
            default:
                break;
        }

        student[] st_arr = new student[5];
        System.out.print("학생 번호 5번 입력 : ");
        Scanner sc = new Scanner(System.in);

        for(int i=0; i<st_arr.length; i++) {
            String tmp = sc.nextLine();
            int num = Integer.parseInt(tmp);
            st_arr[i] = new student(num, "temporary name");
        }

        for(int i=0; i<st_arr.length; ++i) {
            for(int j=0; j<st_arr.length-i-1; ++j) {
                if(st_arr[j].compareTo(st_arr[j+1]) == -1) {
                    student.switch_location(st_arr[j], st_arr[j+1]);
                }
            }
        }

        sc.close();

        for(int i=0; i<st_arr.length; i++) {
            st_arr[i].print_stuNo();
        }
    }
}
