package com.nhnacademy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PipeTest {
    // Pipe의 타입 파라미터를 명시합니다. 여기서는 String 메시지를 사용한다고 가정합니다.
    Pipe<Message<String>> pipe;
    Logger logger = LogManager.getLogger();

    @BeforeEach
    public void setUp() {
        Pipe.resetIds();
        // 제네릭을 사용하여 Pipe 인스턴스를 생성합니다.
        pipe = new Pipe<>(1, 10);
    }

    @Test
    void testUniquePipeId() {
        Pipe<Message<String>> pipe1 = new Pipe<>(2, 5);
        logger.info("Pipe ID TEST : " + pipe1.getPipeId() + " : " + pipe1.getRemainCapacity());
        assertThrows(IllegalArgumentException.class, () -> {
            Pipe<Message<String>> pipe2 = new Pipe<>(2, 5);
            logger.info(pipe2.getPipeId() + " : " + pipe2.getRemainCapacity());
        });
    }

    @Test
    public void testAddMessage() {
        // Message<String> 타입의 메시지를 생성하여 추가합니다.
        assertTrue(pipe.addMessage(new Message<String>(1) {}));
        assertEquals(9, pipe.getRemainCapacity());
    }

    @Test
    public void testCapacityLimit() {
        for (int i = 2; i <= 11; i++) {
            // Message<String> 타입의 메시지를 생성하여 추가합니다.
            assertTrue(pipe.addMessage(new Message<String>(i) {}));
        }
        // 용량 초과로 인한 추가 실패를 테스트합니다.
        assertFalse(pipe.addMessage(new Message<String>(12) {}));
    }
}
