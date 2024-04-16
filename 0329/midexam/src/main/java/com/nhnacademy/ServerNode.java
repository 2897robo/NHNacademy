package com.nhnacademy;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNode extends Node implements Runnable {
    private ServerSocket serverSocket;

    public ServerNode(int nodeId, String nodeName, int port) throws IOException {
        super(nodeId, nodeName);
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        try {
            System.out.println(getNodeName() + "가 포트 " + serverSocket.getLocalPort() + "에서 실행 중...");

            while (!serverSocket.isClosed()) {
                Socket clientSocket = serverSocket.accept(); // 클라이언트 연결 대기
                new Thread(new ClientHandler(clientSocket)).start(); // 연결된 클라이언트를 위한 새 스레드 시작
            }
        } catch (IOException e) {
            System.out.println("서버에서 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                 
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    out.println(inputLine); // 에코 메시지 전송
                }
            } catch (IOException e) {
                System.out.println("클라이언트 핸들러에서 오류 발생: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.out.println("소켓 종료 중 오류 발생: " + e.getMessage());
                }
            }
        }
    }

    public void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("서버 종료 중 오류 발생: " + e.getMessage());
        }
    }
}
