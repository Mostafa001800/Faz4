package com.example.faz3.exception;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException(String s) {
        super(s);
    }
}
