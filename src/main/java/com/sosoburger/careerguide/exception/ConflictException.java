package com.sosoburger.careerguide.exception;

public class ConflictException extends RuntimeException{
    public ConflictException(String error) {
        super(error);
    }
}
