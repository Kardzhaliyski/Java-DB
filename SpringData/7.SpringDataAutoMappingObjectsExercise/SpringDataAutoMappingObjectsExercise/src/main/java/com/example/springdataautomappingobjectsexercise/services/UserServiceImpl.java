package com.example.springdataautomappingobjectsexercise.services;

import com.example.springdataautomappingobjectsexercise.models.User;
import com.example.springdataautomappingobjectsexercise.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UserServiceImpl implements UserService{

    private final Scanner sc;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.sc = new Scanner(System.in);
    }

    private void startMenu() {
        System.out.println(
                "------------------------------------------------------\n" +
                        "--- Menu ---\n" +
                        "------------\n" +
                        "-- 1 : Login\n" +
                        "-- 2 : Register\n" +
                        "------------------------------------------------------");

        switch (sc.nextLine().trim()) {
            case "1": {
                login();
                break;
            } case "2": {
                register();
                break;
            } default: {

            }
        }

    }

    public void login() {
    }

    public void register() {
        User.Builder builder = User.getBuilder();

        setEmail(builder);
        setPassword(builder);
        setFullName(builder);
        User user = builder.build();

        try {
            save(user);
        } catch (IllegalStateException e) {
            System.out.println("User with this email already exists!");
        }

        startMenu();
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
                builder.setPassword(password, confirmPassword);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void save(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new IllegalStateException("User already exists in database with this email address!");
        } else {
            userRepository.save(user);
        }
    }


}