package com.nhnacademy;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

// T는 메시지의 내용을 저장하는 타입을 나타냅니다.
public abstract class Message<T> {
    private static final Set<Integer> ids = new HashSet<>();
    private int messageId;
    private LocalDateTime messageTime;
    private T content; // 제네릭 타입으로 content를 선언

    protected Message(int messageId) {
        if (!ids.add(messageId)) {
            throw new IllegalArgumentException("메세지 ID가 중복됩니다.");
        }
        this.messageId = messageId;
        this.messageTime = LocalDateTime.now();
        this.content = null; // 초기값은 null로 설정
    }

    public int getMessageId() {
        return messageId;
    }

    public LocalDateTime getMessageTime() {
        return messageTime;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    // 테스트 목적으로만 사용됩니다.
    protected static void resetIds() {
        ids.clear();
    }
}