package com.nhnacademy.homework.service;

import com.nhnacademy.homework.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final String HASH_NAME = "Member";
    private final RedisTemplate<String, Object> redisTemplate;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(RedisTemplate<String, Object> redisTemplate, PasswordEncoder passwordEncoder) {
        this.redisTemplate = redisTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    public void createMember(Member member) {
        if (this.findMemberById(member.getId()) != null) {
            throw new RuntimeException("Member already exists");
        }
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        redisTemplate.opsForHash().put(HASH_NAME, member.getId(), member);
    }

    public Member findMemberById(String id) {
        return (Member) redisTemplate.opsForHash().get(HASH_NAME, id);
    }

    public List<Member> findAllMembers() {
        Map<Object, Object> membersMap = redisTemplate.opsForHash().entries(HASH_NAME);
        return membersMap.values().stream()
                .map(member -> (Member) member)
                .collect(Collectors.toList());
    }

    public void deleteMember(String id) {
        redisTemplate.opsForHash().delete(HASH_NAME, id);
    }
}
