import java.util.Scanner;

public class Whatday02 {
    static int[] daysInMonth  = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static int[] daysInLeapMonth  = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static String[] monthNames = {"January", "Feburary", "March", "April", "May", "June", "July", "August", "Sepetember", "October", "November", "December"};
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("연도를 입력 : ");
            String line = scanner.nextLine();
            int yearNum = Integer.parseInt(line);

            boolean isLeapYear = (yearNum % 4 == 0) && (yearNum%100 != 0 || yearNum%400 == 0);

            int maxDayNum = isLeapYear ? 366:365;

            /*
            if(isLeapYear) {
                System.out.println("윤년 맞음");
            }
            else {
                System.out.println("윤년 아님");
            }
            */
            
            System.out.print("Enter a day number between 1 abd 365: ");
            line = scanner.nextLine();
            int dayNum = Integer.parseInt(line);

            if(dayNum<1 || dayNum>maxDayNum) {
                throw new IllegalArgumentException("Out of Range");
            }

            int monthNum = 0; 

            if(isLeapYear) {
                for (int days: daysInLeapMonth) {
                    if (dayNum <= days) {
                        break;
                    }
                    else {
                        dayNum -= days;
                        monthNum++;
                    }
                }
            }
            else {
                for (int days: daysInMonth) {
                    if (dayNum <= days) {
                        break;
                    }
                    else {
                        dayNum -= days;
                        monthNum++;
                    }
                }
            }


            String monthName = monthNames[monthNum];

            System.out.printf("%s, %d \n", monthName, dayNum);
            scanner.close();
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }
}