package com.example.TestCase.entyties.exceptions;

public class TaskIsNotExistException extends NullPointerException {
    public TaskIsNotExistException() {
        super("Задача не существует.");
    }
}
