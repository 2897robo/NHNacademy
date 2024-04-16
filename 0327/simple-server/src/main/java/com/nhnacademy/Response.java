package com.nhnacademy;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Response extends Thread {
    static final String PATH = System.getProperty("user.dir") + "/simple-server/www/";
    Socket socket;
    String method;
    String fileName;
    List<File> files;
    FileReader fr;

    Response(String method, String fileName, Socket socket) {
        this.method = method;
        this.fileName = fileName;
        this.socket = socket;
        this.files = new ArrayList<>(); // ArrayList로 초기화
        for (File file : new File(PATH).listFiles()) { // 파일 목록을 List에 추가
            this.files.add(file);
        }
    }

    @Override
    public void run() {
        try(PrintWriter writer = new PrintWriter(socket.getOutputStream())) {
                writer.write(PATH + " 에서 " + fileName + " 에 대한 " + method + " 요청에 대한 서버로부터의 응답입니다.\n");
                writer.write("\n");
                File requestedFile = new File(PATH + fileName);

                if(fileName.equals("/")) {
                    for(int i=0; i<files.size(); i++) {
                        writer.printf("%d. %s\n", i+1, files.get(i).getName());
                    }
                }

                else if(files.contains(requestedFile)) {
                    if(requestedFile.exists() && !requestedFile.isDirectory() && requestedFile.canRead()) {
                        writer.write("HTTP/1.1 200 OK\r\n");
                        writer.write("Content-Type: text/html; charset=utf-8\r\n"); // Content-Type 설정
                        writer.write("Content-Length: " + requestedFile.length() + "\r\n"); // Content-Length 설정
                        writer.write("\r\n"); // 헤더와 바디 사이의 빈 줄

                        fr = new FileReader(requestedFile);
                        char[] buffer = new char[1024];
                        while (fr.read(buffer) != -1) {
                            writer.write(buffer);
                        }
                        fr.close();
                    }
                    else if(requestedFile.exists() && !requestedFile.isDirectory() && !requestedFile.canRead()) {
                        writer.write("HTTP/1.1 403 Forbidden\r\n");
                        writer.write("파일에 읽기 권한이 없습니다.\r\n");
                    }
                }

                else if(!files.contains(requestedFile)) {
                    writer.write("HTTP/1.1 404 Not Found\r\n");
                    writer.write("파일이 존재하지 않습니다.\r\n");
                }

                writer.write("\n");
                writer.flush();
                System.out.println("응답 실행됨");

        } catch (Exception e) {
            //
        }
    }
}