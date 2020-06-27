package com.bilyoner.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundEntityException extends RuntimeException {

    public NotFoundEntityException(final String msg) {
        super(msg);
    }

}
