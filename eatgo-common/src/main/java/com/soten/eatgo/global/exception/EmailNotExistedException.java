package com.soten.eatgo.global.exception;

import static com.soten.eatgo.global.exception.ExceptionMessage.MESSAGE_EMAIL_NOT_REGISTERED;

public class EmailNotExistedException extends IllegalArgumentException {

    public EmailNotExistedException(String email) {
        super(MESSAGE_EMAIL_NOT_REGISTERED + email);
    }

}
