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

        // 서버 소켓을 생성함
        try{
            ServerSocket serverSocket = new ServerSocket(port);

            // Dispatcher에게 ServerSocket을 전달하도록 수정
            Dispatcher dispatcher = new Dispatcher();
            dispatcher.dispatch(serverSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}