package com.nhnacademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Handler extends Thread {
    private Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                String requestLine;

                while((requestLine=br.readLine()) != null) {
                    System.out.println("클라이언트의 요청 : " + requestLine);
                    new Request(requestLine, socket).start();
                }

                System.out.println("클라이언트의 접속을 종료합니다.");
                socket.close();
        } catch (Exception e) {
            //
        }
    }
}