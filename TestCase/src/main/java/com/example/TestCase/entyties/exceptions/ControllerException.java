package com.example.TestCase.entyties.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ControllerException {
    private int status;
    private String message;
    private Date timestamp;
    public ControllerException(int status, String message){
        this.status=status;
        this.message=message;
        this.timestamp=new Date();
    }
}
