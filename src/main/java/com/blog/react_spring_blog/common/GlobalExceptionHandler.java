package com.blog.react_spring_blog.common;

import com.blog.react_spring_blog.common.exception.MemberException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<String> handleMemberException(MemberException e){
        return new ResponseEntity<>(e.getMessage(),e.getStatus());
    }
}
