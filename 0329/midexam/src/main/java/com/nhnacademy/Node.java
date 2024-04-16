package com.nhnacademy;

import java.util.HashSet;
import java.util.Set;

public abstract class Node {
    private static final Set<Integer> ids = new HashSet<>();
    private int nodeId;
    private String nodeName;

    protected Node(int nodeId, String nodeName) {
        if (!ids.add(nodeId)) {
            throw new IllegalArgumentException("노드 ID가 중복됩니다.");
        }
        this.nodeId = nodeId;
        this.nodeName = nodeName;
    }

    public int getNodeId() {
        return nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    // 테스트 목적으로만 사용됩니다.
    protected static void resetIds() {
        ids.clear();
    }
}
