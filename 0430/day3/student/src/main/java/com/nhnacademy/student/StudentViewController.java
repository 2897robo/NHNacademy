package com.nhnacademy.student;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentViewController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        String studentId = req.getParameter("id");

        //todo id null check
        if (studentId == null || studentId.isEmpty()){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "학생 ID가 필요");
            return "";
        }

        Student student = studentRepository.getStudentById(studentId);

        if(student == null){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 학생을 찾을 수 없음");
            return "";
        }

        //todo student 조회
        req.setAttribute("student",student);

        return "/student/view.jsp";
    }
}