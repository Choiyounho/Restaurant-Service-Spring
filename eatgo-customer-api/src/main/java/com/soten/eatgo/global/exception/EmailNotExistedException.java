package com.soten.eatgo.global.exception;

public class EmailNotExistedException extends RuntimeException{

    public EmailNotExistedException(String email) {
        super("Email is not registered" + email);
    }

}
