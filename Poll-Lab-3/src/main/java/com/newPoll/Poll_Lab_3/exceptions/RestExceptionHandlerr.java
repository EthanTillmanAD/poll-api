package com.newPoll.Poll_Lab_3.exceptions;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class RestExceptionHandlerr extends ResponseEntityExceptionHandler {

    @Autowired
     MessageSource messageSource;


    @ExceptionHandler(ResoureceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResoureceNotFoundException rnfe, HttpServletRequest request){

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetails.setTitle("Resource Not Found");
        errorDetails.setDetails(rnfe.getMessage());
        errorDetails.setDeveloperMessage(rnfe.getClass().getName());

        ValidationError validationError = new ValidationError();
        validationError.setMessage(rnfe.getMessage());
        validationError.setCode(rnfe.toString());
        List<ValidationError> errors = new ArrayList<>();

        errors.add(validationError);

       errorDetails.getErrors().put("Not Found", errors);

        return new ResponseEntity<>(errorDetails, null, HttpStatus.NOT_FOUND);
    }




    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setStatus(status.value());
        errorDetails.setTitle("Message Not Readable");
        errorDetails.setDetails(ex.getMessage());
        errorDetails.setDeveloperMessage(ex.getClass().getName());

        ValidationError validationError = new ValidationError();

        validationError.setCode(ex.toString());
        validationError.setMessage(ex.getMessage());

        List<ValidationError> errors = new ArrayList<>();

        errors.add(validationError);

        errorDetails.getErrors().put("Not Readable", errors);


        return handleExceptionInternal(ex, errorDetails, headers, status, request);
    }


}






























































