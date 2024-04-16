package com.nhnacademy;

public class SimpleConsumer<T> implements Consumer<T> {
    private Pipe<Message<T>> connectedPipe;

    public SimpleConsumer(Pipe<Message<T>> connectedPipe) {
        this.connectedPipe = connectedPipe;
    }

    @Override
    public T receive() {
        if (this.connectedPipe == null) {
            System.out.println("파이프에 연결되지 않았습니다.");
        }

        Message<T> message = this.connectedPipe.getNextMessage();
        return message.getContent();
    }
}