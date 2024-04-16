package com.nhnacademy;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {
    public static void main(String[] args) throws IOException {
        Options options = new Options();
        Manager manager = new Manager();
        
        Option addOption = new Option("a", "add", false, "Add");   //데이터 추가
        Option typeOption = new Option("t", "type", true, "Type"); //데이터 종류
        Option idOption = new Option("i", "id", true, "ID");     //아이디
        Option nameOption = new Option("n", "name", true, "Name"); //이름
        Option listOption = new Option("l", "list", false, "List"); //목록을 보여줌
        Option countOption = new Option("c", "count", true, "Count"); //대전 횟수
        Option winOption = new Option("w", "win", true, "Win"); //승리 횟수
        Option helpOption = new Option("h", "help", false, "Help"); //도움말
        Option energyOption = new Option("e", "energy", true, "Energy"); //체력
        Option attackOption = new Option("at", "attack", true, "Attack"); //공격력
        Option defenceOption = new Option("d", "defence", true, "Defence"); //방어력
        Option moveSpeedOption = new Option("m", "moving-speed", true, "Moving Speed"); //이동속도
        Option attackSpeedOption = new Option("A", "attack-speed", true, "Attack Speed"); //공격속도
        Option historyOption = new Option("L", "history", false, "History"); //변경이력
        Option fileOption = new Option("f", "db-file", true, "Data Storage File"); //데이터 저장 파일

        options.addOption(addOption);
        options.addOption(typeOption);
        options.addOption(idOption);
        options.addOption(nameOption);
        options.addOption(listOption);
        options.addOption(countOption);
        options.addOption(winOption);
        options.addOption(helpOption);
        options.addOption(energyOption);
        options.addOption(attackOption);
        options.addOption(defenceOption);
        options.addOption(moveSpeedOption);
        options.addOption(attackSpeedOption);
        options.addOption(historyOption);
        options.addOption(fileOption);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(options, args);
            if (commandLine.hasOption(helpOption.getOpt())) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("recoder", options);
            }
            if(commandLine.hasOption(addOption.getOpt())) {
                if(commandLine.hasOption(typeOption.getOpt()) && commandLine.getOptionValue(typeOption.getOpt()).equals("user")) {
                    System.out.println("Write User");
                    String tmp = commandLine.getOptionValue(idOption.getOpt());
                    int id = Integer.parseInt(tmp);
                    String name = commandLine.getOptionValue(nameOption.getOpt());
                    manager.write_user(id, name);
                }
            }
            if(commandLine.hasOption(addOption.getOpt())) {
                if(commandLine.hasOption(typeOption.getOpt()) && commandLine.getOptionValue(typeOption.getOpt()).equals("record")) {
                    System.out.println("Write Record");
                    String tmp_count = commandLine.getOptionValue(countOption.getOpt());
                    int count = Integer.parseInt(tmp_count);
                    String tmp_win = commandLine.getOptionValue(winOption.getOpt());
                    int win = Integer.parseInt(tmp_win);
                    manager.write_record(count, win);
                }
            }
            if(commandLine.hasOption(listOption.getOpt())) {
                System.out.println("Read User");
                manager.read_user();
                System.out.println("Read Record");
                manager.read_record();
            }
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
}