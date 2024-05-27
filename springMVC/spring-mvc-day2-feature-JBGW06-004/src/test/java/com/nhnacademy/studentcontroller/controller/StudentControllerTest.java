package com.nhnacademy.studentcontroller.controller;

import com.nhnacademy.studentcontroller.domain.Student;
import com.nhnacademy.studentcontroller.domain.StudentRegisterRequest;
import com.nhnacademy.studentcontroller.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("giuk", "2897", "김기욱", "tmp@gmail.com", 100, "good");
    }

    @Test
    void testViewStudent() throws Exception {
        Mockito.when(studentRepository.getStudent("giuk")).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/giuk"))
                .andExpect(status().isOk())
                .andExpect(view().name("studentView"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().attribute("student", Student.pwdMasked(student)));
    }

    @Test
    void testViewStudentNotFound() throws Exception {
        Mockito.when(studentRepository.getStudent("unknown")).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/unknown"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testWithoutScore() throws Exception {
        Mockito.when(studentRepository.getStudent("giuk")).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/giuk")
                        .param("hideScore", "true"))
                .andExpect(status().isOk())
                .andExpect(view().name("studentView"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().attributeExists("hide"))
                .andExpect(model().attribute("student", Student.pwdMasked(student)));
    }

    @Test
    void testModifyStudent() throws Exception {
        Student updatedStudent = new Student("giuk", "newpwd", "newname", "newemail@gmail.com", 90, "excellent");
        Mockito.when(studentRepository.updateStudent(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(updatedStudent);

        mockMvc.perform(MockMvcRequestBuilders.post("/student/giuk/modify")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "giuk")
                        .param("password", "newpwd")
                        .param("name", "newname")
                        .param("email", "newemail@gmail.com")
                        .param("score", "90")
                        .param("comment", "excellent"))
                .andExpect(status().isOk())
                .andExpect(view().name("studentView"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().attribute("student", Student.pwdMasked(updatedStudent)));
    }

    @Test
    void testStudentModifyForm() throws Exception {
        Mockito.when(studentRepository.getStudent("giuk")).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/giuk/modify"))
                .andExpect(status().isOk())
                .andExpect(view().name("studentModify"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().attribute("student", Student.pwdMasked(student)));
    }
}
