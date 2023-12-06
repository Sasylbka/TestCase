package com.example.TestCase.entyties.DTO;

import com.example.TestCase.entyties.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
}
