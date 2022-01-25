package com.example.demo.exception;

public class BadParamException extends RuntimeException{
    public BadParamException(String s) {
        super(s);
    }
}
