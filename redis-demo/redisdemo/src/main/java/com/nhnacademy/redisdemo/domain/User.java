package com.nhnacademy.redisdemo.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("User")
@Getter
@Setter
public class User implements Serializable {
    @Id
    private String id;
    private String username;
    private String password;
    private String role;

    // getters and setters
}
