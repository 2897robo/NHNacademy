package com.nhnacademy;

public class TerminalOutNode<T> extends SimpleConsumer<T> {
    T sysoutput;

    public TerminalOutNode(Pipe<Message<T>> connectedPipe) {
        super(connectedPipe);
    }
    
    public void readFromFlowAndPrint() {
        sysoutput = receive();
        System.out.println(sysoutput);
    }
}