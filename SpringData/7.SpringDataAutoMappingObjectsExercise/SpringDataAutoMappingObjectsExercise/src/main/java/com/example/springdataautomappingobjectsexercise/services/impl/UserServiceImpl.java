package com.example.springdataautomappingobjectsexercise.services.impl;

import com.example.springdataautomappingobjectsexercise.models.entities.Game;
import com.example.springdataautomappingobjectsexercise.models.entities.User;
import com.example.springdataautomappingobjectsexercise.models.menus.enums.MenuDelimiter;
import com.example.springdataautomappingobjectsexercise.repositories.UserRepository;
import com.example.springdataautomappingobjectsexercise.services.ReaderService;
import com.example.springdataautomappingobjectsexercise.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final ReaderService sc;
    private final UserRepository userRepository;
    private User user;

    public UserServiceImpl(ReaderService sc, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.sc = sc;
    }

    public User login() {
        User user = null;
        while (user == null) {
            System.out.print("Enter email address: ");
            String email = sc.nextLine().trim();
            System.out.print("Enter password: ");
            String password = sc.nextLine().trim();
            user = userRepository.findUserByEmailAndPassword(email, password);
            if (user == null) {
                System.out.println("Wrong email or password! Please try again.");
            }
        }

        System.out.println("Login successfully!");
        this.user = user;
        return user;
    }

    public void register() {
        User.Builder builder = User.getBuilder();

        setEmail(builder);
        setPassword(builder);
        setFullName(builder);
        User user = builder.build();
        if (userRepository.existsByEmail(user.getEmail())) {
            System.out.println("User with this email already exists!");
            return;
        }

        save(user);
        System.out.println("Registration success!");
    }

    @Override
    public void logout() {
        user = null;
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

    @Override
    public void save(User user) {
            userRepository.save(user);
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void printOwnedGames() {
        if (user.getOwnedGames() == null || user.getOwnedGames().size() == 0) {
            System.out.println("No owned games.");
            return;
        }
        System.out.println(MenuDelimiter.LONG_LINE);
        System.out.println("Owned games:");
        for (Game game : user.getOwnedGames().values()) {
            System.out.println(game);
        }
    }
}
