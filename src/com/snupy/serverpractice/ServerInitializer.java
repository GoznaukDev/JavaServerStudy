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
            Socket connection;


            // 서버에 접속된 연결로부터 한 줄을 읽어와 콘솔에 출력함
            while (true) {
                connection = serverSocket.accept();
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader  = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();

                System.out.println("Read: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}