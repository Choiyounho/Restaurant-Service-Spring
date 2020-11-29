package com.soten.eatgo.global.exception;

import static com.soten.eatgo.global.exception.ExceptionMessage.PASSWORD_WRONG;

public class PasswordWrongException extends RuntimeException{

    public PasswordWrongException() {
        super(PASSWORD_WRONG);
    }

}
