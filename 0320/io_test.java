import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class io_test {
    static final String PATH = "./output.txt";

    public static void main(String[] args) {

        try (
            DataOutputStream out = new DataOutputStream(new FileOutputStream(PATH));
            DataInputStream in = new DataInputStream(new FileInputStream(PATH));
        ){
            out.writeInt(100);
            out.writeBoolean(false);
            out.writeDouble(50.5);

            int i = in.readInt();
            boolean b = in.readBoolean();
            double d = in.readDouble();

            System.out.println(i);
            System.out.println(b);
            System.out.println(d);

        } catch(Exception e) {
            System.err.println(e);
        }
    }
}