package com.soten.eatgo.global.exception;

import static com.soten.eatgo.global.exception.ExceptionMessage.MESSAGE_PASSWORD_WRONG;

public class PasswordWrongException extends IllegalArgumentException {

    public PasswordWrongException() {
        super(MESSAGE_PASSWORD_WRONG);
    }

}
