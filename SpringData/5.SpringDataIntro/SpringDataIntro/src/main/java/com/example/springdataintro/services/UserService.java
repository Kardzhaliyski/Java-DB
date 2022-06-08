package com.example.springdataintro.services;

import com.example.springdataintro.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void registerUser(User user);
    User getUserByUsername(String username);
}
