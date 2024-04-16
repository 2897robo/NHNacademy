package com.nhnacademy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionNode<T, R> implements Producer<R>, Consumer<T> {
    private final List<Pipe<Message<R>>> inputProducers = new ArrayList<>();
    private final List<Pipe<Message<T>>> outputConsumers = new ArrayList<>();
    private Function<T, R> transformer;
    private static int messageId;
    
    // SimpleMessage를 제너릭 클래스로 재정의
    static class SimpleMessage<R> extends Message<R> {
        SimpleMessage(int messageId) {
            super(messageId);
        }
    }

    public FunctionNode(Function<T, R> transformer) {
        this.transformer = transformer;
    }

    public void addInputProducer(Pipe<Message<R>> inputProducer) {
        inputProducers.add(inputProducer);
    }

    public void addOutputConsumer(Pipe<Message<T>> outputConsumer) {
        outputConsumers.add(outputConsumer);
    }

    @Override
    public void produce(R messageContent) {
        Message<R> message = new SimpleMessage<R>(messageId++);
        message.setContent(messageContent);
        for (Pipe<Message<R>> producer : inputProducers) {
            producer.addMessage(message);
        }
    }

    @Override
    public T receive() {
        for (Pipe<Message<T>> consumer : outputConsumers) {
            Message<T> message = consumer.getNextMessage();
            if (message != null) {
                T content = message.getContent();
                R transformedContent = transformer.apply(content);
                produce(transformedContent);
                return content;
            }
        }
        throw new UnsupportedOperationException("No message received");
    }
}
