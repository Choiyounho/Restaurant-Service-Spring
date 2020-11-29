package com.soten.eatgo.global.exception;

import static com.soten.eatgo.global.exception.ExceptionMessage.MESSAGE_NOT_FOUND_RESTAURANT;

public class RestaurantNotFoundException extends IllegalArgumentException {

    public RestaurantNotFoundException(Long id) {
        super(MESSAGE_NOT_FOUND_RESTAURANT + id);
    }

}
