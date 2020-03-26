package com.bot.maker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotValidDataException extends RuntimeException{

    public NotValidDataException(String message) {
        super(message);
    }
}
