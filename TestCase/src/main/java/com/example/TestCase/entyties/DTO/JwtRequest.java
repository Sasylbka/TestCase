package com.example.TestCase.entyties.DTO;

import lombok.Data;

@Data
public class JwtRequest {
    private String password;
    private String username;
}
