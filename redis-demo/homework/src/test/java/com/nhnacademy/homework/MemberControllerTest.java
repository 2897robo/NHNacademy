package com.nhnacademy.homework;

import com.nhnacademy.homework.controller.MemberController;
import com.nhnacademy.homework.domain.Member;
import com.nhnacademy.homework.domain.Role;
import com.nhnacademy.homework.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private MemberService memberService;
//
//    @Test
//    @WithMockUser(roles = "ADMIN")
//    public void createMember_success() throws Exception {
//        Member member = new Member();
//        member.setId("1");
//        member.setName("John");
//        member.setPassword("password");
//        member.setAge(30);
//        member.setRole(Role.MEMBER);
//
//        when(memberService.createMember(any(Member.class))).thenReturn(member);
//
//        mockMvc.perform(post("/members")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(member)))
//                .andExpect(status().isOk());
//
//        verify(memberService, times(1)).createMember(any(Member.class));
//    }
//
//    @Test
//    @WithMockUser(roles = "ADMIN")
//    public void getMemberById_success() throws Exception {
//        Member member = new Member();
//        member.setId("1");
//        member.setName("John");
//        member.setPassword("password");
//        member.setAge(30);
//        member.setRole(Role.MEMBER);
//
//        when(memberService.getMemberById("1")).thenReturn(member);
//
//        mockMvc.perform(get("/members/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("John"));
//
//        verify(memberService, times(1)).getMemberById("1");
//    }

    // Additional test cases for pagination, deletion, and authorization
}
