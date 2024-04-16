package com.nhnacademy;

public class AddNode extends OperationNode {
    public AddNode(int nodeId, String nodeName) {
        super(nodeId, nodeName, 2, 1); // 덧셈은 2개의 입력과 1개의 출력을 가집니다.
    }

    @Override
    protected void performOperation() {
        double sum = inputValues.get(0) + inputValues.get(1);
        outputValues.clear();
        outputValues.add(sum);
    }

    @Override
    protected double getResult() {
        return outputValues.get(0);
    }
}