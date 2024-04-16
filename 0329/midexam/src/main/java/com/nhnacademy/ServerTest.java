package com.nhnacademy;

import java.io.IOException;

public class ServerTest {
    public static void main(String[] args) {
        try {
            ServerNode serverNode = new ServerNode(1, "EchoServer", 12345);
            Thread serverThread = new Thread(serverNode);
            serverThread.start();
            System.out.println("서버가 실행되었습니다.");
        } catch (IOException e) {
            System.out.println("서버를 시작하는 중 오류 발생: " + e.getMessage());
        }
    }
}