package com.nhnacademy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActiveNodeTest {

    static class TestNode extends ActiveNode {
        public TestNode(int nodeId, String nodeName) {
            super(nodeId, nodeName);
        }

        @Override
        protected void perform() {
            // 테스트를 위해 비어 있는 상태로 둠
        }
    }

    private TestNode testNode;

    @BeforeEach
    void setUp() {
        // 테스트를 위해 ID와 이름을 설정
        testNode = new TestNode(1, "TestNode");
        // 각 테스트마다 Node의 ID 목록을 초기화
        Node.resetIds();
    }

    @Test
    void testInitialState() {
        assertEquals(ActiveNode.State.START, testNode.getCurrentState(), "초기 상태는 START 여야 합니다.");
    }

    @Test
    void testInitializeState() {
        testNode.setCurrentState(ActiveNode.State.INITIALIZE);
        assertEquals(ActiveNode.State.INITIALIZE, testNode.getCurrentState(), "상태가 INITIALIZE로 변경되어야 합니다.");
    }

    @Test
    void testIdleStateAfterInitialize() {
        testNode.setCurrentState(ActiveNode.State.INITIALIZE);
        testNode.idle();
        assertEquals(ActiveNode.State.IDLE, testNode.getCurrentState(), "INITIALIZE 이후 상태는 IDLE이어야 합니다.");
    }

    @Test
    void testPerformState() {
        testNode.setCurrentState(ActiveNode.State.PERFORM);
        assertEquals(ActiveNode.State.PERFORM, testNode.getCurrentState(), "상태가 PERFORM으로 변경되어야 합니다.");
    }

    @Test
    void testFinalizeState() {
        testNode.setCurrentState(ActiveNode.State.FINALIZE);
        assertEquals(ActiveNode.State.FINALIZE, testNode.getCurrentState(), "상태가 FINALIZE로 변경되어야 합니다.");
    }
}