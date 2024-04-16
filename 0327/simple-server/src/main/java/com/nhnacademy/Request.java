package com.nhnacademy;

import java.net.Socket;

public class Request extends Thread {
    Socket socket;
    String requestLine;
    String method;
    String fileName;

    public Request(String requestLine, Socket socket) {
        this.requestLine = requestLine;
        this.socket = socket;
    }

    public void Request_split() {
        String[] requestParts = requestLine.split(" ");
        method = requestParts[0];
        fileName = requestParts[1];
        System.out.println("요청 메소드 : " + method);
        System.out.println("요청 파일명 : " + fileName);
    }

    @Override
    public void run() {
        Request_split();
        new Response(method, fileName, socket).start();
    }
}