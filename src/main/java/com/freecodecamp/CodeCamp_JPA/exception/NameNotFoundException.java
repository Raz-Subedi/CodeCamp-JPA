package com.freecodecamp.CodeCamp_JPA.exception;

public class NameNotFoundException extends RuntimeException{
    public NameNotFoundException(String message){
        super(message);
    }
}
