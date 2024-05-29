package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/name")
    public String name() {
        return "김기욱";
    }

    @GetMapping("/member")
    public Member member() {
        return new Member("엄준식은 살아있다", 25, Role.ADMIN);
    }

    @PostMapping("/members")
    public Member addMember(@RequestBody Member member) {
        return member;
    }

    @GetMapping("/me")
    public Member getMember(Requester requester){
        System.out.println("IP : " + requester.getIp() + ", AGENT : "  + requester.getUserAgent());
        Member member = new Member("신건영", 10, Role.ADMIN);
        return member;
    }
}
