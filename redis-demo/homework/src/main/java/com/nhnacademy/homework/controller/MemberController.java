package com.nhnacademy.homework.controller;

import com.nhnacademy.homework.domain.Member;
import com.nhnacademy.homework.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 멤버 생성
    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody Member member) {
        memberService.createMember(member);
        return ResponseEntity.ok().build();
    }

    // 멤버 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable String id) {
        Member member = memberService.findMemberById(id);
        return ResponseEntity.ok(member);
    }

    // 멤버 전체 조회
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.findAllMembers();
        return ResponseEntity.ok(members);
    }

    // 멤버 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable String id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }
}
