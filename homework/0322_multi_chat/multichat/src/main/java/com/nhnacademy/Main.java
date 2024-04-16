/*
 * 서버 기본 기능 구현
 * 서버 관리 명령 client_list 외 미구현
 * 서버 설정 정보 미구현
 * 서버 동작, 닫음 로거 구현
 * 클라이언트 기본 기능 구현
 * json 파일I/O 접속요청 미구현
 * json 파일I/O 메시지 전송 구현 (상대방 target_id 구현 실패)
 * json 파일I/O 접속자 명단 확인 미구현
 */

package com.nhnacademy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Main {
    static Server server = new Server();
    static Client client = new Client();

    public static void main(String[] args) {
        Options options = new Options();
        Option hostOption = new Option("h", "host", true, "Host");
        Option portOption = new Option("p", "port", true, "port");
        options.addOption(hostOption);
        options.addOption(portOption);
        
        CommandLineParser parser = new DefaultParser();
        try{
            CommandLine commandLine = parser.parse(options, args);
            if(commandLine.hasOption(hostOption.getOpt()) && commandLine.hasOption(portOption.getOpt())) {
                System.out.println("Client Mode");
                String tmp = commandLine.getOptionValue(portOption.getOpt());
                String host = commandLine.getOptionValue(hostOption.getOpt());
                int port = Integer.parseInt(tmp);
                client.start(host, port);
            }
            else if(commandLine.hasOption(portOption.getOpt())) {
                System.out.println("Server Mode");
                String tmp = commandLine.getOptionValue(portOption.getOpt());
                int port = Integer.parseInt(tmp);
                server.start(port);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}