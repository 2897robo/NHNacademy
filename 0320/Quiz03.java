import java.io.IOException;
import java.net.Socket;

public class Quiz03 {
    public static void main(String[] args) {
        // tag::createSocket[]
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("서버에 연결되었습니다.");
            System.out.println(socket.getInetAddress().getHostAddress());
            System.out.println(socket.getPort());
            System.out.println(socket.getLocalSocketAddress());
            System.out.println(socket.getLocalPort());

            socket.close();
        } catch (IOException e) {
            System.err.println(e);
        }
        // end::createSocket[]
    }
}