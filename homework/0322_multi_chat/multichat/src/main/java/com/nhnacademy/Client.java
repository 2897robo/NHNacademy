package com.nhnacademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public void start(String host, int port) {
        try {
            socket = new Socket(host, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            // 새로운 스레드에서 서버로부터의 메시지를 읽는다.
            new Thread(() -> {
                try {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            // 사용자 입력 처리
            BufferedReader userInputBR = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("사용자의 ID를 입력해주세요 : ");
            String userInput;
            while ((userInput = userInputBR.readLine()) != null) {
                writer.println(userInput);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
