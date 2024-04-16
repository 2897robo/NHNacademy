package com.nhnacademy;

public abstract class ActiveNode extends Node {
    public static enum State {
        START,
        INITIALIZE,
        PERFORM,
        IDLE,
        FINALIZE,
        TERMINATED
    }

    private State currentState;
    private boolean shouldTerminate = false;

    protected ActiveNode(int nodeId, String nodeName) {
        super(nodeId, nodeName);
        this.currentState = State.START;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public boolean getShouldTerminate() {
        return this.shouldTerminate;
    }

    public void setShouldTerminate(boolean shouldTerminate) {
        this.shouldTerminate = shouldTerminate;
    }

    protected void start() {
        if(currentState != State.START) {
            throw new IllegalStateException("시작 상태에서 START 상태가 아닙니다.");
        }
        initialize();
    }

    protected void initialize() {
        setCurrentState(State.INITIALIZE);
        idle();
    }

    protected void idle() {
        setCurrentState(State.IDLE);
        if(getShouldTerminate()) {
            finalizeNode();
        } else {
            perform();
        }
    }

    protected void perform() {
        setCurrentState(State.PERFORM);
        if(getShouldTerminate()) {
            finalizeNode();
        } else {
            idle();
        }
    }

    protected void finalizeNode() {
        setCurrentState(State.FINALIZE);
        terminated();
    }

    protected void terminated() {
        setCurrentState(State.TERMINATED);
    }
}