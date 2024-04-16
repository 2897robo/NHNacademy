package com.nhnacademy;

import java.util.Random;

public class TimerNode extends SimpleProducer<Object> {
    private final int intervalMillis;
    private final Random random = new Random();

    public TimerNode(Pipe<Message<Object>> connectedPipe, int intervalMillis) {
        super(connectedPipe);
        this.intervalMillis = intervalMillis;
    }

    public void timerInput() {
        while(true) {
            try {
                Thread.sleep(intervalMillis);
                // 랜덤한 정수 생성
                Integer randomInt = random.nextInt();
                produce(randomInt);

                Thread.sleep(intervalMillis);
                // 랜덤한 문자열 생성
                String randomString = "String" + random.nextInt(100);
                produce(randomString);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("TimerNode 중단됨");
                break;
            }
        }
    }
}
