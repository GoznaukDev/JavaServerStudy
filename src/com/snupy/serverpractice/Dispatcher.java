package com.snupy.serverpractice;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Dispatcher {

    private final int HEADER_SIZE = 6;

    public void dispatch (ServerSocket serverSocket) {
        // 서버 main으로부터 ServerSocket을 받아옵니다.
        // accept를 받아 Socket을 생성
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                demultiplex(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void demultiplex(Socket socket) {
        // 프로토콜을 받으면 헤더정보를 보고 데이터를 분배
        try {
            InputStream inputStream = socket.getInputStream();
            // 헤더 사이즈(6)만큼 읽어들이기
            byte[] buffer = new byte[HEADER_SIZE];
            inputStream.read(buffer);
            String header = new String(buffer);

            // 헤더의 분기점 작성. String switch는 jdk1.7부터 지원
            if (header.equals("0x5001")) {
                StreamSayHelloProtocol sayHelloProtocol = new StreamSayHelloProtocol();
                sayHelloProtocol.handleEvent(inputStream);
            } if (header.equals("0x6001")) {
                StreamUpdateProfileProtocol updateProfileProtocol = new StreamUpdateProfileProtocol();
                updateProfileProtocol.handleEvent(inputStream);
            }   // 프로세스의 종류가 늘어날 때 마다 분기점을 생성해야 한다.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
