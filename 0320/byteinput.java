import java.io.FileInputStream;
import java.io.FileOutputStream;

public class byteinput {
    static final String PATH = "./IO_test.txt";
    public static void main(String[] args) {

        try {
            FileInputStream fis = new FileInputStream(PATH);
            FileOutputStream fos = new FileOutputStream("output.txt");

            int readData = -1;
            byte[] buffer = new byte[512];

            while ((readData = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, readData);
            }

            fis.close();
            fos.close();
        } catch(Exception e) {
            System.err.println(e);
        }
    }
}