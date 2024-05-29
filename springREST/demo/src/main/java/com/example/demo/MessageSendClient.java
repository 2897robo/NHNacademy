package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "messageSendClient", url = "https://hook.dooray.com/services")
public interface MessageSendClient {

    @PostMapping("/{serviceId}/{botId}/{botToken}")
    String sendMessage(@RequestBody MessagePayload messagePayload,
                       @PathVariable("serviceId") String serviceId,
                       @PathVariable("botId") String botId,
                       @PathVariable("botToken") String botToken,
                       @RequestHeader("Content-Type") String contentType);
}
