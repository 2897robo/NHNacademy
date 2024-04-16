package com.nhnacademy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class NodeTest {
    Logger logger = LogManager.getLogger();

    static class ConcreteNode extends Node {
        ConcreteNode(int nodeId, String nodeName) {
            super(nodeId, nodeName);
        }
    }

    @BeforeEach
    void setUp() {
        Node.resetIds(); // 각 테스트 전에 ID 목록을 초기화
    }

    @Test
    void testUniqueNodeId() {
        Node node1 = new ConcreteNode(1, "Node1");
        logger.info("Node ID TEST : " + node1.getNodeId() + " : " + node1.getNodeName());
        assertThrows(IllegalArgumentException.class, () -> {
            Node node2 = new ConcreteNode(1, "Node2");
            logger.info(node2.getNodeId() + " : " + node2.getNodeName());
        });
    }

    @Test
    void testNodeNameChange() {
        Node node = new ConcreteNode(2, "Node1");
        assertEquals("Node1", node.getNodeName());
        logger.info("Node Name TEST : " + node.getNodeId() + " : " + node.getNodeName());
        node.setNodeName("Node2");
        assertEquals("Node2", node.getNodeName());
        logger.info("Node Name TEST : " + node.getNodeId() + " : " + node.getNodeName());
    }
}