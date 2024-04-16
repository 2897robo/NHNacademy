package com.nhnacademy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;

public class OperationNodeTest {
    AddNode addNode;
    DivisionNode divideNode;
    MultiplyNode multiplyNode;
    MinusNode subtractNode;

    @BeforeEach
    public void setUp() {
        Node.resetIds();
        addNode = new AddNode(1, "AddNode");
        divideNode = new DivisionNode(2, "DivisionNode");
        multiplyNode = new MultiplyNode(3, "MultiplyNode");
        subtractNode = new MinusNode(4, "MinusNode");
    }

    @AfterEach
    void tearDown() {
        Message.resetIds();
    }

    @Test
    public void testAddNode() {
        addNode.setInput(0, 5); // Node1의 입력값
        addNode.setInput(1, 3); // Node2의 입력값
        assertEquals(8, addNode.getOutput(0), "AddNode가 올바르게 덧셈을 수행해야 합니다.");
    }

    @Test
    public void testDivisionNode() {
        divideNode.setInput(0, 10); // Node1의 입력값
        divideNode.setInput(1, 2); // Node2의 입력값
        assertEquals(5, divideNode.getOutput(0), "DivisionNode가 올바르게 나눗셈을 수행해야 합니다.");
    }

    @Test
    public void testMultiplyNode() {
        multiplyNode.setInput(0, 4); // Node1의 입력값
        multiplyNode.setInput(1, 5); // Node2의 입력값
        assertEquals(20, multiplyNode.getOutput(0), "MultiplyNode가 올바르게 곱셈을 수행해야 합니다.");
    }

    @Test
    public void testMinusNode() {
        subtractNode.setInput(0, 10); // Node1의 입력값
        subtractNode.setInput(1, 4); // Node2의 입력값
        assertEquals(6, subtractNode.getOutput(0), "MinusNode가 올바르게 뺄셈을 수행해야 합니다.");
    }
}
