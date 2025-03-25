package com.freecodecamp.CodeCamp_JPA.advice;

import com.freecodecamp.CodeCamp_JPA.exception.NameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(NameNotFoundException.class)
    public ResponseEntity<String> handleEmptyInput(NameNotFoundException nameNotFoundException){

        return new ResponseEntity<>("Name not Found Exception", HttpStatus.BAD_REQUEST);
    }
}
