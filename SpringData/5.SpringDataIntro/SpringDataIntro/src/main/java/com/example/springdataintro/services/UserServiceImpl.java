package com.example.springdataintro.services;

import com.example.springdataintro.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springdataintro.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        if(user == null) {
            throw new IllegalStateException("User should not be null");
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            return;
        }

        userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }
}
