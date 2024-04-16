import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Quiz06 {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;

        try {
            Socket socket = new Socket(host, port);
            System.out.println("서버에 연결되었습니다.");

            String line;

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(!(line = input.readLine()).equals("exit")) {
                System.out.println(line);
                socket.getOutputStream().write(line.getBytes());
                socket.getOutputStream().write("\n".getBytes());
            }

            socket.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
