// scurl httpbin.org
// scurl -X GET httpbin.org
// scurl -v -X GET httpbin.org
// scurl -v -H "X-Custom-Header: NA" httpbin.org
// scurl -v -d "{\"hello\": \"world\"}" -H "Content-Type: application/json" httpbin.org
// scurl -v -L http://httpbin.org/status/302
// scurl -F "upload=@file_path" http://httpbin.org/post
/*
URL을 입력 인자로 받아 요청을 보내고, 응답을 화면에 출력한다.

Option으로 GET외에 다른 method(HEAD, POST, PUT, DELETE)로 요청할 수 있다.

POST, PUT 등의 method를 사용할 때는 전송할 데이터를 지정할 수 있다.

기본적으로는 request header와 response header를 출력하지 않지만, option에 따라 출력할 수 있다.

응답의 ContentType을 확인하여 "text/*", "application/json"만 화면에 출력한다.

POST, PUT의 경우 -H로 Content-Type이 지정되지 않으면, application/x-www-form-urlencoded 를 기본 타입으로 사용한다.
 */

package com.nhnacademy;

import java.io.*;
import java.net.Socket;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Main {
    public static void main(String[] args) {
        String version = "HTTP/1.1";
        String command = "GET";
        String location = "/get";
        int port = 80;
        boolean verbose = false;
        String header = "";

        Options options = new Options();
        Option helpOption = new Option("h", false, "help");
        Option verboseOption = new Option("v", false, "verbose, 요청, 응답 헤더를 출력한다.");
        Option headerOption = new Option("H", true, "임의의 헤더를 서버로 전송한다.");
        headerOption.setArgName("line");
        Option dataOption = new Option("d", true, "POST, PUT 등에 데이터를 전송한다.");
        dataOption.setArgName("data");
        Option XmethodOption = new Option("X", true, "사용할 method를 지정한다. 지정되지 않은 경우, 기본값은 GET");
        XmethodOption.setArgName("command");
        Option L30xOption = new Option("L", false, "서버의 응답이 30x 계열이면 다음 응답을 따라 간다.");
        Option FormdataOption = new Option("F", true, "multipart/form-data를 구성하여 전송한다. content 부분에 @filename을 사용할 수 있다.");
        FormdataOption.setArgName("name=content");

        options.addOption(helpOption);
        options.addOption(verboseOption);
        options.addOption(headerOption);
        options.addOption(dataOption);
        options.addOption(XmethodOption);
        options.addOption(L30xOption);
        options.addOption(FormdataOption);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(options, args);
            if(commandLine.hasOption(helpOption.getOpt())) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("scurl [option] url", "Options:", options, "");
            }
            if(commandLine.hasOption(XmethodOption.getOpt())) {
                command = commandLine.getOptionValue(XmethodOption.getOpt());
            }
            if(commandLine.hasOption(verboseOption.getOpt())) {
                verbose = true;
            }
            if(commandLine.hasOption(headerOption.getOpt())) {
                header = commandLine.getOptionValue(headerOption.getOpt());
            }

            if(commandLine.getArgs().length > 0) {
                String host = commandLine.getArgs()[0];

                try(Socket socket = new Socket(host, port)) {
                    System.out.println("소켓 실행 성공");
                    PrintStream writer = new PrintStream(socket.getOutputStream());

                    writer.printf("%s %s %s\r\n", command, location, version);
                    writer.printf("Host: %s\r\n", host);
                    writer.printf("\r\n");

                    Thread receiver = new Thread( () -> {
                        try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                            String inputLine;
                            boolean isBody = false; // 응답 본문의 시작을 추적하기 위한 플래그

                            while ((inputLine = br.readLine()) != null) {
                                if (inputLine.isEmpty()) {
                                    isBody = true; // 첫 번째 빈 줄을 만나면, 이후의 내용은 응답 본문임
                                    continue; // 본문의 첫 줄부터 출력하기 위해 현재의 빈 줄은 건너뜀
                                }
                                if (isBody) {
                                    System.out.println(inputLine); // 응답 본문만 출력
                                }
                            }
                        } catch (IOException e) {
                            System.err.println(e.getMessage());
                        }
                    } );

                    Thread verboseReceiver = new Thread(() -> {
                        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                            String inputLine;
                            boolean isBody = false; // 응답 본문의 시작을 추적하기 위한 플래그
                            StringBuilder responseHeaders = new StringBuilder();
                            StringBuilder responseBody = new StringBuilder();
                            String contentType = null;
                    
                            while ((inputLine = br.readLine()) != null) {
                                if (!isBody) {
                                    responseHeaders.append("< " + inputLine).append("\n");
                                    if (inputLine.startsWith("Content-Type: ")) {
                                        contentType = inputLine.split(":")[1].trim();
                                    }
                                }
                                if (inputLine.isEmpty() && !isBody) {
                                    isBody = true; // 첫 번째 빈 줄을 만나면, 이후의 내용은 응답 본문임
                                    if(commandLine.hasOption(verboseOption.getOpt())) {
                                        System.out.print(responseHeaders.toString());
                                    }
                                    continue; // 본문의 첫 줄부터 출력하기 위해 현재의 빈 줄은 건너뜀
                                }
                                if (isBody) {
                                    responseBody.append(inputLine).append("\n");
                                }
                            }
                            if (contentType != null && (contentType.contains("text/") || contentType.contains("application/json"))) {
                                System.out.print(responseBody.toString());
                            }
                        } catch (IOException e) {
                            System.err.println(e.getMessage());
                        }
                    });

                    if(verbose) {
                        System.out.println("> " + command + " " + location + " " + version);
                        System.out.println("> Host: " + host);
                        if(commandLine.hasOption(headerOption.getOpt())) {
                            System.out.println("> " + header);
                        }
                        System.out.println("> ");

                        verboseReceiver.start();
                        verboseReceiver.join();
                    }
                    else if(!verbose) {
                        receiver.start();
                        receiver.join();
                    }


                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            } else {
                System.err.println("URL을 입력하세요.");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}