package com.exceptions;

public class UserApiException extends RuntimeException {

    public UserApiException(){
        super("Internal Server Error");
    }
}
