package com.example.TestCase.services;

import com.example.TestCase.entyties.DTO.JwtRequest;
import com.example.TestCase.entyties.DTO.JwtResponse;
import com.example.TestCase.entyties.DTO.RegisterRequest;
import com.example.TestCase.entyties.DTO.UserDTO;
import com.example.TestCase.entyties.User;
import com.example.TestCase.entyties.exceptions.ControllerException;
import com.example.TestCase.utils.JwtToken;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthorizationService {
    private final UserService userService;
    private final JwtToken jwtToken;
    private final AuthenticationManager authenticationManager;
    //Создание токена авторизации
    public ResponseEntity<?> createAuthorizationToken(JwtRequest jwtRequest){
        //Проврека валидности данных
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (jwtRequest.getUsername(), jwtRequest.getPassword()));
        }
        catch (BadCredentialsException e){
            return new ResponseEntity<>(new ControllerException(HttpStatus.UNAUTHORIZED.value(),"Неверный логин или пароль."),HttpStatus.UNAUTHORIZED);
        }
        //Подгрузка пользователя
        UserDetails userDetails= userService.loadUserByUsername(jwtRequest.getUsername());

        //Создание токена
        String token = jwtToken.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
    //Создание нового пользователя
    public ResponseEntity<?> createNewUser(RegisterRequest registerRequest){
        //Проверка на идентичность паролей
        if(!Objects.equals(registerRequest.getPassword(), registerRequest.getConfirmPassword())){
            return new ResponseEntity<>(new ControllerException(HttpStatus.BAD_REQUEST.value(),"Пароли не совпадают."),HttpStatus.UNAUTHORIZED);
        }
        //Проверка занято ли имя пользователя
        if(userService.findByUsername(registerRequest.getUsername()).isPresent()){
            return new ResponseEntity<>(new ControllerException(HttpStatus.UNAUTHORIZED.value(),"Пользователь с таким именем уже существует."),HttpStatus.UNAUTHORIZED);
        }
        //Создание пользователя
        User user = userService.createNewUser(registerRequest);

        return ResponseEntity.ok(new UserDTO(user.getId(),user.getUsername(),user.getPassword()));
    }

}
