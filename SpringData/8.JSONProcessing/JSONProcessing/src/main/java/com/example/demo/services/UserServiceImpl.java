package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImpl implements com.example.demo.services.UserService {

    private final UserRepository userRepository;
    private final Random random;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        random = new Random();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getRandomUser() {
        long userCount = userRepository.count();
        int randomUserNumber = random.nextInt((int) userCount);
        Page<User> userPage = userRepository.findAll(PageRequest.of(randomUserNumber, 1));

        if(userPage.hasContent()) {
            return userPage.getContent().get(0);
        }

        return null;
    }
}
