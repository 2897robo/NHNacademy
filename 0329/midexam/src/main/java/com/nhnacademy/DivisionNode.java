package com.nhnacademy;

public class DivisionNode extends OperationNode {
    public DivisionNode(int nodeId, String nodeName) {
        super(nodeId, nodeName, 2, 1); // 뺄셈은 2개의 입력과 1개의 출력을 가집니다.
    }

    @Override
    protected void performOperation() {
        double product = inputValues.get(0) / inputValues.get(1);
        outputValues.clear();
        outputValues.add(product);
    }

    @Override
    protected double getResult() {
        return outputValues.get(0);
    }
}