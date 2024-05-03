package com.nhnacademy.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Random;

@WebListener
public class WebApplicationListener  implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new JsonStudentRepository();
        Random random = new Random();

        for(int i=1; i<=10; i++){
            // ... student 1 ~ 10 생성하기
            String id = String.valueOf(i);
            String name = "Student " + i;
            Gender gender = random.nextBoolean() ? Gender.F : Gender.M;
            // 나이 : random 처리 : 20~30
            int age = random.nextInt(11) + 20;

            Student student = new Student(id, name, gender, age);
            studentRepository.save(student);
        }

        // ... application scope에서 studentRepository 객체에 접근할 수 있도록 구현하기
        context.setAttribute("studentRepository", studentRepository);
    }
}