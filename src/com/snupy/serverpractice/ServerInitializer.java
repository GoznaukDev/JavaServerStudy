package com.snupy.serverpractice;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerInitializer {

    public static void main(String[] args) {

        // 접속에 사용할 포트를 5000으로 설정
        int port = 5000;
        System.out.println("Server ON: " + port);

        Reactor reactor = new Reactor(port);

        reactor.registerHandler(new StreamSayHelloEventHandler());
        reactor.registerHandler(new StreamUpdateProfileEventHandler());

        reactor.startServer();
    }
}