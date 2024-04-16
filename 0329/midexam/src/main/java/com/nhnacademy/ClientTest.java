package com.nhnacademy;

import java.io.*;

public class ClientTest {
    public static void main(String[] args) {
        try {
            ClientNode clientNode = new ClientNode(2, "EchoClient", "localhost", 12345);
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            System.out.println("메시지를 입력하고 Enter 키를 누르세요 ('quit'로 종료):");
            while ((userInput = consoleReader.readLine()) != null && !userInput.equalsIgnoreCase("quit")) {
                clientNode.produce(userInput);
                String response = clientNode.receive();
                System.out.println("서버로부터의 응답: " + response);
            }
            clientNode.close();
            System.out.println("클라이언트를 종료합니다.");
        } catch (IOException e) {
            System.out.println("클라이언트 실행 중 오류 발생: " + e.getMessage());
        }
    }
}
