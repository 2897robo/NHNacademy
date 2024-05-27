package com.nhnacademy.studentcontroller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoStudentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoStudentException(NoStudentException ex) {
        return "error";
    }
}
