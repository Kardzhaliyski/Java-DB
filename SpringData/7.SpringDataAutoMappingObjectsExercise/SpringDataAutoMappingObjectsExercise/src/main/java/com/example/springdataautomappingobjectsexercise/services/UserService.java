package com.example.springdataautomappingobjectsexercise.services;

import com.example.springdataautomappingobjectsexercise.models.User;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UserService {

    public User register() {
        Scanner sc = new Scanner(System.in);
        User.UserBuilder builder = User.getBuilder();

        //todo: add setUsername with check if it already exists in the database

        while (builder.getPassword() == null) {
            try {
                System.out.print("Enter password: ");
                String password = sc.nextLine();
                System.out.print("Confirm password: ");
                String confirmPassword = sc.nextLine();
                builder.setPassword(password,confirmPassword);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        //todo: add setFullName

        return builder.build();
    }
}
