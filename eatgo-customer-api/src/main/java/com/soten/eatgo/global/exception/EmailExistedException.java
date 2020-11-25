package com.soten.eatgo.global.exception;

import static com.soten.eatgo.global.exception.ExceptionMessage.EMAIL_ALREADY_REGISTERED;

public class EmailExistedException extends RuntimeException {

    public EmailExistedException(String email) {
        super(EMAIL_ALREADY_REGISTERED + email);
    }

}
