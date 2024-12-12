package com.newPoll.Poll_Lab_3.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
@ControllerAdvice
public class Test{
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
//
//        ErrorDetails errorDetails = new ErrorDetails();
//        errorDetails.setTimeStamp(new Date().getTime());
//        errorDetails.setStatus(status.value());
//        errorDetails.setTitle("Message Not Readable");
//
//        errorDetails.setDetails(ex.getMessage());
//        errorDetails.setDeveloperMessage(ex.getClass().getName());
//        return handleExceptionInternal(ex, errorDetails, headers, status, request);
//    }
}
