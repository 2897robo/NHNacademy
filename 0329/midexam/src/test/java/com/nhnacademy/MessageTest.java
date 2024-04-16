package com.nhnacademy;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageTest {
    Logger logger = LogManager.getLogger();

    // ConcreteMessage가 String 타입의 메시지를 처리하도록 설정합니다.
    static class ConcreteMessage extends Message<String> {
        ConcreteMessage(int messageId) {
            super(messageId);
        }
    }

    @BeforeEach
    void setUp() {
        // Message.resetIds()는 Message 클래스의 static 메소드이므로, 
        // Message 클래스가 제네릭이 되었더라도 호출 방식에 변화는 없습니다.
        Message.resetIds(); // 각 테스트 전에 ID 목록을 초기화
    }

    @Test
    void testUniqueMessageId() {
        // ConcreteMessage 인스턴스 생성 시 제네릭 타입에 대한 명시는 필요 없습니다.
        // ConcreteMessage 클래스 정의 시 이미 String 타입으로 지정했기 때문입니다.
        Message<String> message1 = new ConcreteMessage(1);
        logger.info("Message ID TEST : " + message1.getMessageId() + " : " + message1.getMessageTime());
        assertThrows(IllegalArgumentException.class, () -> {
            Message<String> message2 = new ConcreteMessage(1);
            logger.info(message2.getMessageId() + " : " + message2.getMessageTime());
        });
    }
}
