package com.nhnacademy.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentRegisterFormController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //todo  /student/register.jsp forward 합니다.
        return "/student/register.jsp";
    }
}