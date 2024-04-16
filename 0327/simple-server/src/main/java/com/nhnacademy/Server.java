package com.nhnacademy;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static int port = 80;

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("서버가 실행되었습니다.");

            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("새로운 클라이언트가 접속되었습니다.");

                new Handler(socket).start();
            }

        } catch (Exception e) {
            System.err.println("서버 오류 발생");
        }
    }
}