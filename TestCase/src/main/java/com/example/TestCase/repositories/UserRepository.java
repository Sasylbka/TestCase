package com.example.TestCase.repositories;

import com.example.TestCase.entyties.Task;
import com.example.TestCase.entyties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
