package com.nhnacademy.studentcontroller.controller;

import com.nhnacademy.studentcontroller.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentLoginController.class)
class StudentLoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    void testLoginForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginForm"));
    }

    @Test
    void testDoLoginSuccess() throws Exception {
        Mockito.when(studentRepository.matches("giuk", "2897")).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "giuk")
                        .param("pwd", "2897"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/student/giuk"));
    }

    @Test
    void testDoLoginFailure() throws Exception {
        Mockito.when(studentRepository.matches("giuk", "wrongpassword")).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "giuk")
                        .param("pwd", "wrongpassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginForm"))
                .andExpect(model().attributeExists("error"));
    }

    @Test
    void testLogout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}
