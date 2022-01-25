package com.example.demo.exception;

import com.example.demo.message.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Validated
class ValidateParametersController{

    // request mapping method omitted

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ResponseMessage> handleConstraintViolationException(ConstraintViolationException e) {
        System.out.println("*************** HEWRE");
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(e.getMessage()));
    }

}
