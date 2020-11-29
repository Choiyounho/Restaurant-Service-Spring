package com.soten.eatgo.global.exception;

import static com.soten.eatgo.global.exception.ExceptionMessage.MESSAGE_EMAIL_ALREADY_REGISTERED;

public class EmailExistedException extends IllegalArgumentException {

    public EmailExistedException(String email) {
        super(MESSAGE_EMAIL_ALREADY_REGISTERED + email);
    }

}
