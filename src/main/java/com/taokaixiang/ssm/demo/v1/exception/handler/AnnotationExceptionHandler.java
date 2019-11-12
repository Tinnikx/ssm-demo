package com.taokaixiang.ssm.demo.v1.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AnnotationExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = NullPointerException.class)
    public String resolveException2(HttpServletRequest request) {
        return request.getServletPath();
    }
}
