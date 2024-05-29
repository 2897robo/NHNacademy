package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class MessagePayload {
    private String botName;
    private String botIconImage;
    private String text;
    private List<Attachment> attachments;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Attachment {
        private String title = "test";
        private String titleLink = "https://hook.dooray.com/services";
        private String text = "text";
        private String color = "red";
    }
}
