import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exam01 {
    public static void main(String[] args) {
        for (String arg : args) {
            try {
                int value = Integer.parseInt(arg);
                System.out.println("int : " + value);
            } catch (NumberFormatException ignore) {
                System.out.println("String : " + arg);
            }
        }
    }
}


/*
    Pattern pattern = Pattern.compile("^\\s*[+-]?(0|[1-9][0-9]{0,9})\\s*$");
    Matcher matcher = pattern.matcher(arg);

    if(matcher.find()) {
        int value = Integer.parseInt(arg.trim());
        System.out.println("int : " + value);
    }
    else {
        System.out.println("String : " + arg);
    }
 */