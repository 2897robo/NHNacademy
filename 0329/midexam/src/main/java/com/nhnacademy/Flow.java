package com.nhnacademy;

import java.util.ArrayList;
import java.util.List;

public class Flow {
    private final List<Node> nodes = new ArrayList<>();
    private final List<Pipe<?>> pipes = new ArrayList<>();

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addPipe(Pipe<?> pipe) {
        pipes.add(pipe);
    }

    public List<Node> getNodes() {
        return new ArrayList<>(nodes);
    }

    public List<Pipe<?>> getPipes() {
        return new ArrayList<>(pipes);
    }

    public Node findNodeById(int nodeId) {
        for (Node node : nodes) {
            if (node.getNodeId() == nodeId) {
                return node;
            }
        }
        return null;
    }

    public Pipe<?> findPipeById(int pipeId) {
        for (Pipe<?> pipe : pipes) {
            if (pipe.getPipeId() == pipeId) {
                return pipe;
            }
        }
        return null;
    }
}