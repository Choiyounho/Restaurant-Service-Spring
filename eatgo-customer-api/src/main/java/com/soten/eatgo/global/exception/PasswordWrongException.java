package com.soten.eatgo.global.exception;

public class PasswordWrongException extends RuntimeException{

    public PasswordWrongException() {
        super("Password is wrong");
    }
}
