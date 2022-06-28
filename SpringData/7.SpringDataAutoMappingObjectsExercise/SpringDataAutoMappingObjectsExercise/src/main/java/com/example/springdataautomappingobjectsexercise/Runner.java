package com.example.springdataautomappingobjectsexercise;

import com.example.springdataautomappingobjectsexercise.models.User;
import com.example.springdataautomappingobjectsexercise.services.UserService;
import com.example.springdataautomappingobjectsexercise.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {

    private final UserService userService;


    @Autowired
    public Runner(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {
        userService.register();

    }
}
