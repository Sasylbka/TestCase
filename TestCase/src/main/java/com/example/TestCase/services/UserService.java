package com.example.TestCase.services;

import com.example.TestCase.entyties.DTO.RegisterRequest;
import com.example.TestCase.entyties.User;
import com.example.TestCase.entyties.exceptions.UserAlreadyExistException;
import com.example.TestCase.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private  UserRepository userRepository;
    private  RoleService roleService;
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private void setUserRepository(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Autowired
    private void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder=passwordEncoder;
    }
    @Autowired
    private void setRoleService(RoleService roleService){
        this.roleService=roleService;
    }
    public Optional<User> findByUsername(String name){
        return userRepository.findByUsername(name);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=findByUsername(username).orElseThrow(()->new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден",username))
        );
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
                );
    }
    public User createNewUser(RegisterRequest registerRequest){
        if(userRepository.findByUsername(registerRequest.getUsername()).isPresent()){
            throw new UserAlreadyExistException(String.format(registerRequest.getUsername()));
        }
        else{
            User user= new User();
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            user.setUsername(registerRequest.getUsername());
            user.setRoles(List.of(roleService.getUserRole()));
            return userRepository.save(user);
        }
    }
}
