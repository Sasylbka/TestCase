package com.example.TestCase.entyties.exceptions;

import org.springframework.security.core.AuthenticationException;
/*Кастомное исключение для существуюшего пользователя*/
public class UserAlreadyExistException extends AuthenticationException {
    public UserAlreadyExistException(String username) {
        super("Пользователь "+username+" не найден");
    }
}
