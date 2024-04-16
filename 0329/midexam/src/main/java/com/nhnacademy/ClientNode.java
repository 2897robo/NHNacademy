package com.nhnacademy;

import java.io.*;
import java.net.Socket;

public class ClientNode extends Node implements Producer<String>, Consumer<String> {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    
    public ClientNode(int nodeId, String nodeName, String serverAddress, int port) throws IOException {
        super(nodeId, nodeName);
        this.socket = new Socket(serverAddress, port);
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void produce(String messageContent) {
        out.println(messageContent);
    }

    @Override
    public String receive() {
        try {
            return in.readLine();
        } catch (IOException e) {
            System.out.println("클라이언트에서 메시지를 읽는 중 오류 발생: " + e.getMessage());
            return null;
        }
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("클라이언트 종료 중 오류 발생: " + e.getMessage());
        }
    }
}
