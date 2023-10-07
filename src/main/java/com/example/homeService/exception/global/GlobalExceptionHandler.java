package com.example.homeService.exception.global;

import com.example.homeService.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity serviceExistHandler(NotFoundException e){
        String response =e.getMessage();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity serviceExistHandler(IllegalStateException e){
        String response=e.getMessage();
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
