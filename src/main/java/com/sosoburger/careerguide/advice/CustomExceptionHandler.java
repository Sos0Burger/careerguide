package com.sosoburger.careerguide.advice;

import com.sosoburger.careerguide.exception.BadRequestException;
import com.sosoburger.careerguide.exception.ForbiddenException;
import com.sosoburger.careerguide.exception.InternalException;
import com.sosoburger.careerguide.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity handle(NotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InternalException.class)
    public ResponseEntity handle(InternalException internalException){
        log.error(internalException.getMessage(), internalException);
        return new ResponseEntity(internalException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity handle(BadRequestException badRequestException){
        log.error(badRequestException.getMessage(), badRequestException);
        return new ResponseEntity(badRequestException.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity handle(ForbiddenException forbiddenException) {
        log.error(forbiddenException.getMessage(), forbiddenException);
        return new ResponseEntity(forbiddenException.getMessage(), HttpStatus.FORBIDDEN);
    }
}
