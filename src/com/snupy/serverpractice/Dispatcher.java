package com.snupy.serverpractice;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Dispatcher {

    private final int HEADER_SIZE = 6;

    public void dispatch (ServerSocket serverSocket, HandleMap handleMap) {
        // 서버 main으로부터 ServerSocket을 받아옵니다.
        // accept를 받아 Socket을 생성
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                demultiplex(socket, handleMap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void demultiplex(Socket socket, HandleMap handleMap) {
        // 프로토콜을 받으면 헤더정보를 보고 데이터를 분배
        try {
            InputStream inputStream = socket.getInputStream();
            // 헤더 사이즈(6)만큼 읽어들이기
            byte[] buffer = new byte[HEADER_SIZE];
            inputStream.read(buffer);
            String header = new String(buffer);

            handleMap.get(header).handleEvent(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
