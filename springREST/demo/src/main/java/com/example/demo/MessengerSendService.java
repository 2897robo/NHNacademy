package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class MessengerSendService {

//    @Autowired
//    private MessageSendClient messageSendClient;
//
//    public void send() {
//        MessagePayload messagePayload = new MessagePayload("김기욱", "", "테스트 메시지", Arrays.asList(new MessagePayload.Attachment()));
//        String serviceId = "3204376758577275363";
//        String botId = "3803317990120691948";
//        String botToken = "jNQJBlJ9SCO4b4p1KxoApQ";
//
//        messageSendClient.sendMessage(messagePayload, serviceId, botId, botToken, "application/json");
//    }

    @Autowired
    private RestTemplate restTemplate;

    public void send(String text, String name) {
        String doorayUrl = "https://hook.dooray.com/services/";
        StringBuilder sb = new StringBuilder(doorayUrl);
        sb.append("");
        sb.append(doorayUrl);
        sb.append("/");
        String requestUrl = sb.toString();

        //HttpEntity
    }
    //restTemplate.exchange();
}
