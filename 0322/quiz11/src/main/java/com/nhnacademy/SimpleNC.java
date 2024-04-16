package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class SimpleNC {
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("l", true, "Listen");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(options, args);

            if (commandLine.hasOption("l")) {
                List<Thread> clientHandlerList = new LinkedList<>();
                List<NetCat> netcatList = new LinkedList<>();

                Thread inputAgent = new Thread( () -> {
                    try {
                        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                        String line;
                        while ((line = input.readLine()) != null) {
                            synchronized(netcatList) {
                                for(NetCat netcat : netcatList) {
                                    netcat.send(line + "\n");
                                }
                            }
                        }
                    } catch (Exception e) {
                    }
                });

                Thread outputAgent = new Thread( () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        synchronized(netcatList) {
                            for(NetCat netcat : netcatList) {
                                if(!netcat.isEmptyReceiveQuie()) {
                                    String line = netcat.receive();
                                    System.out.println(line);
                                }
                            }
                        }
                    }
                });



                ServerSocket serverSocket = new ServerSocket(1234);
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        NetCat netCat = new NetCat(serverSocket.accept());
                        Thread thread = new Thread(netCat);
                        System.out.println("CLA 생성");
                        thread.start();
                        System.out.println("CLA Thread 시작");
                        clientHandlerList.add(thread);
                        netcatList.add(netCat);
                        System.out.println("CLA 접속됨");
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
                serverSocket.close();
            } else {
                try (Socket socket = new Socket("localhost", 1234)) {
                    NetCat netcat = new NetCat(socket);
                    Thread thread = new Thread(netcat);
                    System.out.println("Cla 생성");
                    thread.start();

                    Thread inputAgent = new Thread( () -> {
                        try {
                            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                            String line;
                            while ((line = input.readLine()) != null) {
                                netcat.send(line + "\n");
                            }
                        } catch (Exception e) {
                        }
                    });

                    Thread outputAgent = new Thread( () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            if(!netcat.isEmptyReceiveQuie()) {
                                String line = netcat.receive();
                                System.out.println(line);
                            }
                        }
                    });

                    inputAgent.start();
                    outputAgent.start();
                    thread.join();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (org.apache.commons.cli.ParseException | IOException e) {
        }
    }
}