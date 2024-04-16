import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Exam04 {
    public static void main(String[] args) {
        int port = 1234;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while(!Thread.currentThread().isInterrupted()) {
                Socket socket = serverSocket.accept();
                System.out.println(socket.getInetAddress().getHostAddress() + "에 연결 완료");
                socket.getOutputStream().write("Hello!\n".getBytes());
                socket.getOutputStream().flush();
    
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();

       }
    }
}