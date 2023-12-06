package com.example.TestCase.services;

import com.example.TestCase.entyties.Role;
import com.example.TestCase.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public Role getUserRole(){
        return roleRepository.findByName("USER").get();
    }
}
