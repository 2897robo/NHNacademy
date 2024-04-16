package com.nhnacademy;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FlowTest {
    private Flow flow;

    // TestNode 클래스는 Node 클래스를 상속받는 테스트 목적의 구현입니다.
    private static class TestNode extends Node {
        public TestNode(int nodeId, String nodeName) {
            super(nodeId, nodeName);
        }
    }

    @Before
    public void setUp() {
        flow = new Flow();
        Node.resetIds();
        Pipe.resetIds();
    }

    @Test
    public void testAddAndFindNode() {
        Node node1 = new TestNode(1, "Node1");
        Node node2 = new TestNode(2, "Node2");
        flow.addNode(node1);
        flow.addNode(node2);

        assertEquals(2, flow.getNodes().size());
        assertEquals(node1, flow.findNodeById(1));
        assertEquals(node2, flow.findNodeById(2));
    }

    @Test
    public void testAddAndFindPipe() {
        Pipe<Message<String>> pipe1 = new Pipe<>(1, 10);
        Pipe<Message<Integer>> pipe2 = new Pipe<>(2, 5);
        flow.addPipe(pipe1);
        flow.addPipe(pipe2);

        assertEquals(2, flow.getPipes().size());
        assertEquals(pipe1, flow.findPipeById(1));
        assertEquals(pipe2, flow.findPipeById(2));
    }
}
