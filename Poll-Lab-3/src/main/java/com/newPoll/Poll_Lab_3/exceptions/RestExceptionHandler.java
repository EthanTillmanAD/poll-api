package com.newPoll.Poll_Lab_3.exceptions;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


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

        return new ResponseEntity<>(errorDetails, null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException manve, HttpServletRequest request){

        ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestPath == null){
            requestPath = request.getRequestURI();
        }
        errorDetails.setTitle("Validation Failed");
        errorDetails.setDetails("Input validation failed");
        errorDetails.setDeveloperMessage(manve.getClass().getName());

        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
        for (FieldError fieldError: fieldErrors){
            List<ValidationError> validationErrorList = errorDetails.getErrors().get(fieldError.getField());
            if (validationErrorList == null){
                validationErrorList = new ArrayList<>();
                errorDetails.getErrors().put(fieldError.getField(), validationErrorList);
            }
            ValidationError validationError = new ValidationError();
            validationError.setCode(fieldError.getCode());
            validationError.setMessage(fieldError.getDefaultMessage());
            validationErrorList.add(validationError);
        }
        return new ResponseEntity<>(errorDetails, null, HttpStatus.BAD_REQUEST);
    }


//  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
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
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//        ErrorDetails errorDetail = new ErrorDetails();
//        errorDetail.setTimeStamp(new Date().getTime());
//        errorDetail.setStatus(status.value());
//        errorDetail.setTitle("Message Not Readable");
//        errorDetail.setDetails(ex.getMessage());
//        errorDetail.setDeveloperMessage(ex.getClass().getName());
//
//        return handleExceptionInternal(ex, errorDetail, headers, status, request);
//    }

//    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//        // implementation removed for brevity
//        ErrorDetails errorDetail = new ErrorDetails();
//        errorDetail.setTimeStamp(new Date().getTime());
//        errorDetail.setStatus(status.value());
//        errorDetail.setTitle("Message Not Readable");
//        errorDetail.setDetails(manve.getMessage());
//        errorDetail.setDeveloperMessage(manve.getClass().getName());
//        return handleExceptionInternal(manve, errorDetail, headers, status, request);
//    }
}


//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public @ResponseBody ErrorDetails handleValidationError(MethodArgumentNotValidException manve, HttpServletRequest request){
//        ErrorDetails errorDetails = new ErrorDetails();
//
//        errorDetails.setTimeStamp(new Date().getTime());
//        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
//        String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri");
//
//        if (requestPath == null){
//            requestPath = request.getRequestURI();
//        }
//
//        errorDetails.setTitle("Validation Failed");
//        errorDetails.setDetails("Input validation Failed");
//        errorDetails.setDeveloperMessage(manve.getMessage());
//
//        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
//
//        for (FieldError fieldError: fieldErrors){
//
//            List<ValidationError> validationErrorList = errorDetails.getErrors().get(fieldError.getField());
//            if (validationErrorList == null){
//                validationErrorList = new ArrayList<>();
//                errorDetails.getErrors().put(fieldError.getField(), validationErrorList);
//            }
//
//            ValidationError validationError = new ValidationError();
//            validationError.setCode(fieldError.getCode());
//            validationError.setMessage(messageSource.getMessage(fieldError, null));
//            validationErrorList.add(validationError);
//
//        }
//
//        return errorDetails;
//
//
//
//    }
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public @ResponseBody ErrorDetails handleValidationError(MethodArgumentNotValidException manve, HttpServletRequest request) {
//        ErrorDetails errorDetail = new ErrorDetails();
//        // Populate errorDetail instance
//        errorDetail.setTimeStamp(new Date().getTime());
//        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
//        String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri");
//        if(requestPath == null) {
//            requestPath = request.getRequestURI();
//        }
//        errorDetail.setTitle("Validation Failed");
//        errorDetail.setDetails("Input validation failed");
//        errorDetail.setDeveloperMessage(manve.getClass().getName());
//        // Create ValidationError instances
//        List<FieldError> fieldErrors =  manve.getBindingResult().getFieldErrors();
//        for(FieldError fe : fieldErrors) {
//            List<ValidationError> validationErrorList = errorDetail.getErrors().
//                    get(fe.getField());
//            if(validationErrorList == null) {
//                validationErrorList = new ArrayList<ValidationError>();
//                errorDetail.getErrors().put(fe.getField(),
//                        validationErrorList);
//            }
//
//            ValidationError validationError = new ValidationError();
//            validationError.setCode(fe.getCode());
//            validationError.setMessage(messageSource.getMessage(fe, null));
//            validationErrorList.add(validationError);
//        }
//        return errorDetail;
//    }


