import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Quiz09 {
    public static void main(String[] args) {
        int port = 1234;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while(!Thread.currentThread().isInterrupted()) {
                Socket socket = serverSocket.accept();
                System.out.println(socket.getInetAddress().getHostAddress() + " 클라이언트가 연결 완료");

                String line;
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while(!(line = input.readLine()).equals("exit") ) {
                    System.out.println("터미널에서 입력한 메시지 : " + line);
                    socket.getOutputStream().write(("루프백 메시지 : "+line+"\n").getBytes());
                    socket.getOutputStream().flush();
                }
                
                System.out.println(socket.getInetAddress().getHostAddress() + " 클라이언트 연결 종료");
                socket.getOutputStream().write("서버와의 연결이 종료됩니다.\n".getBytes());
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
       }
    }
}