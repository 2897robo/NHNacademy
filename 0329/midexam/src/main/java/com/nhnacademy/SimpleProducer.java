package com.nhnacademy;

public class SimpleProducer<T> implements Producer<T> {
    static class TextMessage<T> extends Message<T> {
        protected TextMessage(int messageId) {
            super(messageId);
        }
    }
    private Pipe<Message<T>> connectedPipe;
    private static int messageIdGenerator = 0; // 간단한 메시지 ID 생성기

    public SimpleProducer(Pipe<Message<T>> connectedPipe) {
        this.connectedPipe = connectedPipe;
    }

    @Override
    public void produce(T sysinput) {
        if (this.connectedPipe == null) {
            System.out.println("파이프에 연결되지 않았습니다.");
            return;
        }

        // 메시지 ID를 생성하고, 메시지 객체를 생성합니다.
        int messageId = ++messageIdGenerator; // 메시지 ID 생성
        Message<T> message = new TextMessage<T> (messageId);
        message.setContent(sysinput);

        // 생성된 메시지 객체를 파이프에 추가합니다.
        boolean isAdded = this.connectedPipe.addMessage(message);
        if (isAdded) {
            System.out.println("메시지가 파이프에 추가되었습니다: " + sysinput);
        } else {
            System.out.println("파이프가 가득 찼습니다. 메시지를 추가할 수 없습니다.");
        }
    }
}
