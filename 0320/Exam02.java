import java.net.Socket;
import java.util.*;

public class Exam02 {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;

        try(Socket socket = new Socket(host, port)) {
            System.out.println("서버 연결 성공");
            Scanner sc = new Scanner(System.in);
            String tmp = "";
            while(!tmp.equals("exit\n")) {
               tmp = sc.nextLine() + "\n";
               socket.getOutputStream().write(tmp.getBytes());
            }
            sc.close();
        } catch(Exception e) {
            System.err.println(host + ":" + port + "에 연결 불가");
        }
    }
}