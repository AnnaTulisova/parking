package com.tulisova.parking.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String email) {
        super(String.format("Пользователь с такой почтой (%s) уже существует", email));
    }
}