package com.example.springdataautomappingobjectsexercise.services;

import com.example.springdataautomappingobjectsexercise.models.User;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UserService {

    private Scanner sc;

    public UserService() {
        this.sc = new Scanner(System.in);
    }

    public User register() {

        User.Builder builder = User.getBuilder();

        setEmail(builder);
        setPassword(builder);
        setFullName(builder);
        return builder.build();
    }

    private void setEmail(User.Builder builder) {
        while (builder.getEmail() == null) {
            try {
                System.out.print("Enter email address: ");
                builder.setEmail(sc.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        //todo: check if it already exists in the database
    }

    private void setFullName(User.Builder builder) {
        while (builder.getFullName() == null) {
            try {
                System.out.print("Enter full name: ");
                builder.setFullName(sc.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setPassword(User.Builder builder) {
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
    }




}
