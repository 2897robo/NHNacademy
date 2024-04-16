package com.nhnacademy;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// Pipe 클래스를 제네릭으로 선언합니다. T는 Message 클래스의 타입 파라미터입니다.
public class Pipe<T extends Message<?>> {
    private static final Set<Integer> ids = new HashSet<>();
    private int pipeId;
    // PriorityQueue의 타입도 T로 지정하여, T 타입의 Message만을 처리합니다.
    private PriorityQueue<T> queue;
    private int capacity;

    public Pipe(int pipeId, int capacity) {
        if (!ids.add(pipeId)) {
            throw new IllegalArgumentException("파이프 ID가 중복됩니다.");
        }
        this.pipeId = pipeId;
        this.capacity = capacity;
        // 메시지 시간을 기준으로 하는 Comparator를 사용하여 PriorityQueue를 초기화합니다.
        this.queue = new PriorityQueue<>(capacity, Comparator.comparing(Message::getMessageTime));
    }

    public int getPipeId() {
        return pipeId;
    }

    public boolean addMessage(T message) {
        if(queue.size() >= capacity) {
            return false;
        }
        return queue.offer(message);
    }

    public T getNextMessage() {
        return queue.poll();
    }

    public int getRemainCapacity() {
        return capacity - queue.size();
    }

    // 테스트 목적으로만 사용됩니다.
    protected static void resetIds() {
        ids.clear();
    }
}