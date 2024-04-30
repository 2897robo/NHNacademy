package com.nhnacademy.student;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "studentUpdateServlet", urlPatterns = "/student/update")
public class StudentUpdateServlet extends HttpServlet {
    private StudentRepository studentRepository;
    @Override
    public void init(ServletConfig config) throws ServletException {
        //todo init studentRepository
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //todo 학생조회

        req.setAttribute("student",student);

        //todo forward : /student/register.jsp

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //todo null check

        //todo student 저장

        studentRepository.update(student);

        //todo /student/view?id=student1 <-- redirect
    }
}