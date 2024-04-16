package com.nhnacademy;

public interface Producer<T> {
    void produce(T messageContent);
}