package com.newPoll.Poll_Lab_3.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResoureceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;



    public ResoureceNotFoundException(String message) {
        super(message);
    }


}
