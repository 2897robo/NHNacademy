import java.util.Scanner;

public class Whatday {
    static int[] daysInMonth  = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static String[] monthNames = {"January", "Feburary", "March", "April", "May", "June", "July", "August", "Sepetember", "October", "November", "December"};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("1~365 입력 : ");
        int dayNum = sc.nextInt();

        int monthNum = 0;

        for(int days: daysInMonth) {
            if(dayNum <= days) {
                break;
            }
            else {
                dayNum -= days;
                monthNum++;
            }
        }

        /*
        //
        // To do: add code here
        // 

        // calculate Month Number and days in Month using if statement
        if (dayNum >= 31) {     // January
            monthNum++;
            dayNum -= 31;
        }

        if (dayNum >= 28) {     // Feburary
            monthNum++;
            dayNum -= 28;
        }

        if (dayNum >= 31) {     // March
            monthNum++;
            dayNum -= 31;
        }

        if (dayNum >= 30) {     // April
            monthNum++;
            dayNum -= 30;
        }

        if (dayNum >= 31) {     // May
            monthNum++;
            dayNum -= 31;
        }

        if (dayNum >= 30) {     // June
            monthNum++;
            dayNum -= 30;
        }

        if (dayNum >= 31) {     // July
            monthNum++;
            dayNum -= 31;
        }

        if (dayNum >= 31) {     // August
            monthNum++;
            dayNum -= 31;
        }

        if (dayNum >= 30) {     // September
            monthNum++;
            dayNum -= 30;
        }

        if (dayNum >= 31) {     // October
            monthNum++;
            dayNum -= 31;
        }

        if (dayNum >= 30) {     // November
            monthNum++;
            dayNum -= 30;
        }

        if (dayNum >= 31) {     // December
            monthNum++;
            dayNum -= 31;
        }
        */

        String monthName;

        /*
       //  Assign month name using switch statement
       switch(monthNum) {
            case 0:
                monthName = "January";
                break;
            case 1:
                monthName = "Feburary";
                break;
            case 2:
                monthName = "March";
                break;
            case 3:
                monthName = "April";
                break;
            case 4:
                monthName = "May";
                break;
            case 5:
                monthName = "June";
                break;
            case 6:
                monthName = "July";
                break;
            case 7:
                monthName = "August";
                break;
            case 8:
                monthName = "September";
                break;
            case 9:
                monthName = "October";
                break;
            case 10:
                monthName = "November";
                break;
            case 11:
                monthName = "December";
                break;
            default:
                monthName = "Not Set";
        }
        */

        monthName = monthNames[monthNum];

        System.out.printf("%s, %d ", monthName, dayNum);

        sc.close();
    }
}