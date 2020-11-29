package com.soten.eatgo.global.exception;


import static com.soten.eatgo.global.exception.ExceptionMessage.MESSAGE_NOT_FOUND_USER;

public class UserNotFoundException extends IllegalArgumentException {

    public UserNotFoundException(Long id) {
        super(MESSAGE_NOT_FOUND_USER + id);
    }

}
