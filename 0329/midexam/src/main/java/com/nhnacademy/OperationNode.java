package com.nhnacademy;

import java.util.ArrayList;
import java.util.List;

public abstract class OperationNode extends Node {
    protected List<Double> inputValues;
    protected List<Double> outputValues;
    private int inputCount;
    private int outputCount;

    public OperationNode(int nodeId, String nodeName, int inputCount, int outputCount) {
        super(nodeId, nodeName);
        this.inputCount = inputCount;
        this.outputCount = outputCount;
        this.inputValues = new ArrayList<>(inputCount);
        for (int i = 0; i < inputCount; i++) {
            inputValues.add(null);
        }
        this.outputValues = new ArrayList<>(outputCount);
    }

    public void setInput(int index, double value) {
        if (index >= 0 && index < inputCount) {
            inputValues.set(index, value);
            checkAndPerformOperation();
        } else {
            throw new IndexOutOfBoundsException("입력 포트 n개 초과");
        }
    }

    protected abstract void performOperation();

    private void checkAndPerformOperation() {
        if (inputValues.stream().allMatch(java.util.Objects::nonNull)) {
            performOperation();
            for (int i = 0; i < outputCount; i++) {
                outputValues.add(getResult());
            }
        }
    }

    protected abstract double getResult();

    public double getOutput(int index) {
        if (index >= 0 && index < outputValues.size()) {
            return outputValues.get(index);
        } else {
            throw new IndexOutOfBoundsException("출력 포트 m개 초과");
        }
    }
}
