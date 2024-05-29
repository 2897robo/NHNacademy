package com.example.demo;

import lombok.Getter;

@Getter
public class Requester {
    String ip;
    String userAgent;

    public Requester(String ip, String userAgent) {
        this.ip = ip;
        this.userAgent = userAgent;
    }
}
