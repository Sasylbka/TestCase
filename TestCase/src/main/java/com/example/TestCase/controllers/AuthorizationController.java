package com.example.TestCase.controllers;

import com.example.TestCase.config.SecurityConfig;
import com.example.TestCase.entyties.DTO.JwtRequest;
import com.example.TestCase.entyties.DTO.RegisterRequest;
import com.example.TestCase.services.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Import(SecurityConfig.class)
@RestController
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    /*Эндпоинт для авторизации пользователя*/
    @PostMapping("/authorization")
    public ResponseEntity<?> createAuthorizationToken(@RequestBody JwtRequest jwtRequest){
       return authorizationService.createAuthorizationToken(jwtRequest);
    }

    /*Эндпоинт для регистрации пользователя*/
    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegisterRequest registerRequest){
       return authorizationService.createNewUser(registerRequest);
    }
}
