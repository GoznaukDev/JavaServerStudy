package com.snupy.serverpractice;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {

    public static void main(String[] args) {
        System.out.println("Client ON");

        // 클라이언트에서 소켓을 새로 생성한 후 메시지를 전송하고 연결을 종료함
        try {
            String message;

            Socket socket1 = new Socket("127.0.0.1", 5000); // protocol: '헤더|데이터'로 구성됨
            OutputStream out1 = socket1.getOutputStream();
            message = "0x5001|홍길동|22";
            out1.write(message.getBytes());
            socket1.close();

            Socket socket2 = new Socket("127.0.0.1", 5000);  // protocol: '헤더|데이터'로 구성됨
            OutputStream out2 = socket2.getOutputStream();
            message = "0x6001|hong|1234|홍길동|22|남성";
            out2.write(message.getBytes());
            socket2.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}