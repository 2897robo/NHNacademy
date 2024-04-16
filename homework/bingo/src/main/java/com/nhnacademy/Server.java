package com.nhnacademy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Server {
    public static void main(String[] args) {
        Server serverMode = new Server();

        Options options = new Options();

        Option helpOption = new Option("h", "help", false, "Help");
        Option listenOption = new Option("l", true, "server로 동작시 입력 받은 port로 listen");

        options.addOption(helpOption);
        options.addOption(listenOption);

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine commandLine = parser.parse(options, args);
            if(commandLine.hasOption(helpOption.getOpt())) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("snc [option] [hostname] [port]", options);
            }

            if(commandLine.hasOption(listenOption.getOpt())) {
                System.out.println("Server Mode 로 실행합니다.");
                String tmp_port = commandLine.getOptionValue(listenOption.getOpt());
                int tmp = Integer.parseInt(tmp_port);
                serverMode.server_Mode(tmp);
            }

        } catch (Exception e) {
            System.err.println(e);
            System.exit(0);
        }
    }

    public void server_Mode(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("서버가 " + port + " 포트에서 클라이언트의 연결을 기다리고 있습니다...");

            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                 BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

                System.out.println("클라이언트가 연결되었습니다.");

                // 클라이언트로부터 메시지를 받고 출력하는 스레드
                Thread inputThread = new Thread(() -> {
                    try {
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            System.out.println("클라이언트가 보낸 메세지 : " + inputLine);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                inputThread.start();

                // 표준 입력으로부터 메시지를 받아 클라이언트에게 전송
                String userInput;
                while ((userInput = stdIn.readLine()) != null) {
                    out.write(userInput);
                    out.newLine();
                    out.flush();
                }

            } catch (Exception e) {
                System.err.println("클라이언트와의 연결에 문제가 발생했습니다: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("서버를 생성할 수 없습니다: " + e.getMessage());
            e.printStackTrace();
        }
    }
}