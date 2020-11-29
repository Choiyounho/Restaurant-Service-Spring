package com.soten.eatgo.global.exception;

import static com.soten.eatgo.global.exception.ExceptionMessage.EMAIL_NOT_REGISTERED;

public class EmailNotExistedException extends RuntimeException{

    public EmailNotExistedException(String email) {
        super(EMAIL_NOT_REGISTERED + email);
    }

}
