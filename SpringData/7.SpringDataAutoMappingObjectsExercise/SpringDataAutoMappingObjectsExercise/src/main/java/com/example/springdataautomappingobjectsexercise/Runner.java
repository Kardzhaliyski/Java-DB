package com.example.springdataautomappingobjectsexercise;

import com.example.springdataautomappingobjectsexercise.models.User;
import com.example.springdataautomappingobjectsexercise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {

    private UserService userService;


    @Autowired
    public Runner(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {
        User.Builder builder = User.getBuilder();
        Scanner sc = new Scanner(System.in);


        User user = userService.register();
        System.out.println(user);

    }
}
