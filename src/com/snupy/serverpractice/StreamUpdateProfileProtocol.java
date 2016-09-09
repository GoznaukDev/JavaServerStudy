package com.snupy.serverpractice;

import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

public class StreamUpdateProfileProtocol {
    // Stream방식으로 데이터를 받아와서 출력

    private static final int DATA_SIZE = 1024;   // 데이터의 맥시멈 사이즈 정의
    private static final int TOKEN_NUM = 5;     // 데이터 구분자의 개수 정의 (파이프'|'로 구분)

    public void handleEvent(InputStream inputStream) {

        try {
            byte[] buffer = new byte[DATA_SIZE];    // 데이터 맥시멈 사이즈 512만큼 데이터를 받아옴
            inputStream.read(buffer);
            String data = new String(buffer);

            String[] params = new String[TOKEN_NUM];
            StringTokenizer token = new StringTokenizer(data, "|"); // 파이프를 파싱

            int i = 0;
            while (token.hasMoreTokens()) {
                params[i] = token.nextToken();
                ++i;
            }
            sayHello(params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sayHello(String[] params) {
        // 파이프를 기준으로 파싱한 데이터를 출력
        System.out.println("UpdateProfile -> id: " + params[0] +
                "               password: " + params[1] +
                "               name: " + params[2] +
                "               age: " + params[3] +
                "               gender: " + params[4]);
    }
}