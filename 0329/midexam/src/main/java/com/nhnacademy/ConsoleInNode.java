package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInNode extends SimpleProducer<Object> { // Object를 사용하여 다양한 타입 처리
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public ConsoleInNode(Pipe<Message<Object>> connectedPipe) {
        super(connectedPipe);
    }

    public void readFromConsoleAndSend() throws IOException {
        String input;
        while((input = br.readLine()) != null) {
            try {
                // 입력된 값이 정수인 경우
                int intValue = Integer.parseInt(input);
                produce(intValue); // int형으로 처리
            } catch (NumberFormatException e) {
                // 입력된 값이 정수가 아닌 경우 (문자열 등)
                produce(input); // String형으로 처리
            }
        }
    }
}
