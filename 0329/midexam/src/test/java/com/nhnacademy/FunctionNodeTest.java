package com.nhnacademy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Function;

public class FunctionNodeTest {
    private FunctionNode<String, Integer> functionNode;
    private Pipe<Message<Integer>> inputPipe;
    private Pipe<Message<String>> outputPipe;

    @BeforeEach
    void setUp() {
        Function<String, Integer> stringToLengthFunction = String::length;
        functionNode = new FunctionNode<>(stringToLengthFunction);

        inputPipe = new Pipe<>(1, 10); // ID 1, capacity 10
        outputPipe = new Pipe<>(2, 10); // ID 2, capacity 10

        functionNode.addInputProducer(inputPipe);
        functionNode.addOutputConsumer(outputPipe);

        Message.resetIds(); // 테스트 시작 전 ID 초기화
        Pipe.resetIds(); // 테스트 시작 전 Pipe ID 초기화
    }

    @AfterEach
    void tearDown() {
        Message.resetIds(); // 테스트 후 ID 초기화
        Pipe.resetIds(); // 테스트 후 Pipe ID 초기화
    }

    @Test
    void testProduce() {
        functionNode.produce(5); // Integer 타입의 메세지 생성
        assertEquals(9, inputPipe.getRemainCapacity(), "Pipe에 메시지가 성공적으로 추가되어야 합니다.");

        Message<Integer> receivedMessage = inputPipe.getNextMessage();
        assertNotNull(receivedMessage, "메시지를 받아야 합니다.");
        assertEquals(5, receivedMessage.getContent(), "전송된 메시지의 내용이 일치해야 합니다.");
    }

    @Test
    void testReceiveAndTransform() {
        Message<String> message = new FunctionNode.SimpleMessage<>(0);
        message.setContent("Hello");
        assertTrue(outputPipe.addMessage(message), "메시지가 성공적으로 추가되어야 합니다.");

        String receivedContent = functionNode.receive();
        assertEquals("Hello", receivedContent, "받은 메시지의 내용이 일치해야 합니다.");

        assertEquals(9, inputPipe.getRemainCapacity(), "변환된 메시지가 입력 파이프에 추가되어야 합니다.");
        Message<Integer> transformedMessage = inputPipe.getNextMessage();
        assertNotNull(transformedMessage, "변환된 메시지를 받아야 합니다.");
        assertEquals(Integer.valueOf(5), transformedMessage.getContent(), "변환된 메시지의 내용이 'Hello' 문자열의 길이와 일치해야 합니다.");
    }

    @Test
    void testReceiveWithNoMessage() {
        assertThrows(UnsupportedOperationException.class, functionNode::receive, "메시지가 없을 경우 예외가 발생해야 합니다.");
    }
}
