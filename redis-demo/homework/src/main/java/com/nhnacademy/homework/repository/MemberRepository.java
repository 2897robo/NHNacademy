package com.nhnacademy.homework.repository;
// Redis 에 멤버 데이터 저장하고 조회하는 기능 담당 (HashOperations 사용)
import com.nhnacademy.homework.domain.Member;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class MemberRepository {
    private static final String KEY = "Member";
    private final RedisTemplate<String, Object> redisTemplate;
    private final HashOperations<String, String, Member> hashOperations;

    public MemberRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void save(Member member) {
        hashOperations.put(KEY, member.getId(), member);
    }

    public Member findById(String id) {
        return (Member) hashOperations.get(KEY, id);
    }

    public Map<String, Member> findAll() {
        return hashOperations.entries(KEY);
    }

    public void delete(String id) {
        hashOperations.delete(KEY, id);
    }
}
