import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Quiz01 {
    public static void main(String[] args) {
        for(String arg : args) {
            Pattern pattern = Pattern.compile(".");
            Matcher matcher = pattern.matcher(arg);
    
            if(matcher.find()) {
                if(arg.equals("-classpath")) {
                    System.out.println("String : --class-path");
                } else {
                    System.out.println("String : " + arg);
                }
            }
        }
    }
}