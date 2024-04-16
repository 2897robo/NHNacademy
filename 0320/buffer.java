import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class buffer {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;

        try(Socket socket = new Socket(host, port)) {
            System.out.println("서버 연결 완료");

            Thread thread = new Thread(() -> {
                try {
                    String line;

                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));     //InputStreamReader 가 byte 형을 char 형으로 바꾸어 준다.

                    while(!(line = br.readLine()).equals("exit")) {
                        System.out.println("서버에서 받은 메시지 : " + line);
                    }
                } catch(Exception e) {
                    System.err.println(e);
                }
            });

            thread.start();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));      //OutputStreamWriter 가 byte 형을 char 형으로 바꾸어 준다.
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str;

            while((str = br.readLine()) != null) {
                bw.write("사용자에게서 수신한 메시지 : " + str);    //버퍼에 쓴다.
                bw.newLine();   //버퍼에 새 라인 추가
                bw.flush();     //버퍼에서 내보내기 -> 서버 전송
            }

            socket.close();

        }
        catch(Exception e) {
            System.err.println(host + " : " + port + "에 연결 불가");
        }
    }
}