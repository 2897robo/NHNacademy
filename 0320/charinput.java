import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class charinput {
    public static void main(String[] args) {
        try (
            BufferedReader br = new BufferedReader(new FileReader("./IO_test.txt"));
            PrintWriter pw = new PrintWriter(new FileWriter("./output.txt"))
        ) { 
            String line;
            
            while((line = br.readLine()) != null) {
                pw.println(line);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}