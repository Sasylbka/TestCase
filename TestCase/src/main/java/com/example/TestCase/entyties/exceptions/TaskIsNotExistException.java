package com.example.TestCase.entyties.exceptions;
/*Кастомное исключение для не существующей задачи*/
public class TaskIsNotExistException extends NullPointerException {
    public TaskIsNotExistException() {
        super("Задача не существует.");
    }
}
