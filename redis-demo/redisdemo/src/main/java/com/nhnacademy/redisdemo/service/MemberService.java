package com.nhnacademy.redisdemo.service;

import com.nhnacademy.redisdemo.request.MemberLoginRequest;
import com.nhnacademy.redisdemo.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MemberService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final String HASH_NAME = "Member";

    public Member saveMember(Member member) {
        UUID uuid = UUID.randomUUID();
        member.setId(uuid.toString());
        redisTemplate.opsForHash().put(HASH_NAME, member.getId(), member);
        return member;
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

    public Member updateMember(String id, Member updatedMember) {
        Member existingMember = findMemberById(id);
        if (existingMember != null) {
            updatedMember.setId(id);
            redisTemplate.opsForHash().put(HASH_NAME, id, updatedMember);
            return updatedMember;
        } else {
            return null;
        }
    }

    public Member login(MemberLoginRequest loginRequest) {
        Member member = findMemberById(loginRequest.getId());
        if (member != null && member.getPassword().equals(loginRequest.getPassword())) {
            return member;
        }
        return null;
    }
}
