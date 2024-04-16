package com.nhnacademy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Server {
    static final String FILE_PATH = "./json/messages.json";
    File file = new File(FILE_PATH);
    FileWriter fileWriter;
    FileReader fileReader;
    static Logger logger = LogManager.getLogger();  //this.getClass().getSimpleName()
    JSONObject testObject;
    JSONArray testArray;
    JSONTokener testJsonTokener;

    private ServerSocket serverSocket;
    private Map<String, PrintWriter> clients = Collections.synchronizedMap(new HashMap<>());

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("서버가 " + port + " 포트에서 실행됩니다...");
            logger.trace("서버 실행 시작");

            // 서버 입력 처리 스레드 시작
            new Thread(this::handleServerInput).start();

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new Handler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 서버 표준 입력 처리 및 모든 클라이언트에게 메시지 전송
    private void handleServerInput() {
        try (BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in))) {
            String input;
            while ((input = serverInput.readLine()) != null) {
                if(input.equals("client_list")) {
                    System.out.println("사용자 목록을 출력합니다.");
                    for(String id : clients.keySet()) {
                        System.out.println(id);
                    }
                }
                broadcast("서버 : " + input);
                record(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 모든 클라이언트에게 메시지를 보낸다.
    private void broadcast(String message) {
        for (PrintWriter writer : clients.values()) {
            writer.println(message);
        }
    }

    // 메시지를 json 형식으로 저장한다.
    private synchronized void record(String message) throws IOException {
        testObject = new JSONObject();

        if(file.exists() && file.length() > 0) {
            fileReader = new FileReader(FILE_PATH);
            testJsonTokener = new JSONTokener(fileReader);
            testArray = new JSONArray(testJsonTokener);
            fileReader.close();
        } else {
            testArray = new JSONArray();
        }

        testObject.put("senderID", "Server");
        testObject.put("type", "Server Notice");
        testObject.put("message", message);

        testArray.put(testObject);

        fileWriter = new FileWriter(FILE_PATH);
        fileWriter.write(testArray.toString(4));
        fileWriter.flush();
        fileWriter.close();
    }



    private class Handler implements Runnable {
        private Socket socket;
        private PrintWriter writer;
        private BufferedReader reader;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                writer = new PrintWriter(socket.getOutputStream(), true);
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // 클라이언트로부터 ID 받기
                String id = reader.readLine();
                if (clients.containsKey(id)) {
                    writer.println("서버 : " + id + " 에서 중복 발생");
                    writer.println("소켓이 닫히며 클라이언트 접속이 종료됩니다.");
                    socket.close();
                    return;
                }
                writer.println("환영합니다. ID : " + id);
                broadcast("새로운 사용자가 입장했습니다 : " + id);
                clients.put(id, writer);
                System.out.println("새로운 사용자가 입장했습니다 : " + id);
                logger.trace("사용자 " + id + " 추가됨.");

                String line;
                while ((line = reader.readLine()) != null) {
                    broadcast(id + " : " + line);
                    record(id, line);
                }

                clients.remove(id);
                broadcast(id + " 사용자가 나갔습니다.");
                System.out.println(id + " 사용자가 나갔습니다.");
                logger.trace("사용자 " + id + " 삭제됨.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 모든 클라이언트에게 메시지를 보낸다.
        private void broadcast(String message) {
            for (PrintWriter writer : clients.values()) {
                writer.println(message);
            }
        }

        // 메시지를 json 형식으로 저장한다.
        private synchronized void record(String id, String message) throws IOException {
            testObject = new JSONObject();

            if(file.exists() && file.length() > 0) {
                fileReader = new FileReader(FILE_PATH);
                testJsonTokener = new JSONTokener(fileReader);
                testArray = new JSONArray(testJsonTokener);
                fileReader.close();
            } else {
                testArray = new JSONArray();
            }

            testObject.put("senderID", id);
            testObject.put("type", "message");
            testObject.put("message", message);

            testArray.put(testObject);

            fileWriter = new FileWriter(FILE_PATH);
            fileWriter.write(testArray.toString(4));
            fileWriter.flush();
            fileWriter.close();
        }
    }
}
