package com.nhnacademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Client clientMode = new Client();

        if(args.length == 2) {
            String ser_host = args[0];
            String ser_tmp_port = args[1];
            int ser_port = Integer.parseInt(ser_tmp_port);
            clientMode.cla_mode(ser_host, ser_port);
        }
    }

    public void cla_mode(String ser_host, int ser_port) {
        try (Socket socket = new Socket(ser_host, ser_port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println(ser_host + " : " + ser_port + " 서버에 연결되었습니다.");

            // 서버로부터 메시지를 받고 출력하는 스레드
            Thread inputThread = new Thread(() -> {
                try {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("서버로부터 받은 메시지 : " + inputLine);
                    }
                } catch (Exception e) {
                    System.err.println("서버로부터 데이터를 받는 중 문제가 발생했습니다: " + e.getMessage());
                    e.printStackTrace();
                }
            });

            inputThread.start();

            // 표준 입력으로부터 메시지를 받아 서버에 전송
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.write(userInput);
                out.newLine();
                out.flush();
            }

        } catch (Exception e) {
            System.err.println("서버에 연결할 수 없습니다: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
