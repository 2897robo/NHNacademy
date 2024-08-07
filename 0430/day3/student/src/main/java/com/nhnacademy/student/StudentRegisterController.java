package com.nhnacademy.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentRegisterController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String genderParam = req.getParameter("gender");
        int age = Integer.parseInt(req.getParameter("age"));

        Gender gender = Gender.M;
        if ("M".equals(genderParam)) {
            gender = Gender.M;
        } else if ("F".equals(genderParam)) {
            gender = Gender.F;
        }

        //todo null check
        if (id == null || name == null) {
            try {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid student data");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "";
        }
        //todo save 구현
        Student student = new Student(id, name, gender, age);
        studentRepository.save(student);

        //todo redirect /student/view?id=student1
        return "redirect:/student/view.do?id=" + id;
    }
}