package com.soten.eatgo.global.exception;


public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super(ExceptionMessage.MESSAGE_NOT_FOUND_USER + id);
    }

}
