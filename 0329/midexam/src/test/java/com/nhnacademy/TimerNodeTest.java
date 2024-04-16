package com.nhnacademy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TimerNodeTest {
    private Pipe<Message<Object>> pipe;
    private TimerNode timerNode;
    Logger logger = LogManager.getLogger();

    @BeforeEach
    void setUp() {
        Message.resetIds(); // Message의 ID 목록을 초기화합니다.
        Pipe.resetIds(); // Pipe의 ID 목록을 초기화합니다.
        pipe = new Pipe<>(1, 10); // ID가 1이고, 용량이 10인 파이프를 생성합니다.
        timerNode = new TimerNode(pipe, 100); // 100ms 간격의 TimerNode를 생성합니다.
    }

    @AfterEach
    void tearDown() {
        // 테스트가 끝날 때마다 ID 목록을 초기화합니다.
        Message.resetIds();
        Pipe.resetIds();
    }

    @Test
    void testMessageIdUniqueness() {
        Message<Object> message1 = new Message<>(1) {};
        logger.info("Message ID TEST : " + message1.getMessageId());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Message<>(1) {});
        assertEquals("메세지 ID가 중복됩니다.", exception.getMessage());
    }

    @Test
    void testAddMessageToPipe() {
        Message<Object> message = new Message<>(2) {};
        message.setContent(new Object());
        assertTrue(pipe.addMessage(message));
    }

    @Test
    void testGetNextMessageFromPipe() {
        Message<Object> message = new Message<>(3) {};
        message.setContent(new Object());
        pipe.addMessage(message);
        assertEquals(message, pipe.getNextMessage());
    }

    @Test
    void testPipeCapacityLimit() {
        for (int i = 0; i < 10; i++) {
            Message<Object> message = new Message<>(i+4) {};
            message.setContent(new Object());
            pipe.addMessage(message);
        }

        Message<Object> overflowMessage = new Message<>(14) {};
        overflowMessage.setContent(new Object());
        assertFalse(pipe.addMessage(overflowMessage));
    }

    @Test
    void testTimerNodeProducesMessages() throws InterruptedException {
        Thread thread = new Thread(timerNode::timerInput);
        thread.start();

        Thread.sleep(500); // 기다려서 TimerNode가 최소한 하나의 정수와 문자열을 생성하게 합니다.

        thread.interrupt();
        thread.join();

        assertNotNull(pipe.getNextMessage().getContent());
        assertNotNull(pipe.getNextMessage().getContent());
    }
}
