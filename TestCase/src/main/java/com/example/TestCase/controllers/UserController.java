package com.example.TestCase.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {

    /*Эндпоинт для просмотра информации о пользователе*/
    @GetMapping("info")
    public String getUserInfo(Principal principal){
        return principal.getName();
    }
}
