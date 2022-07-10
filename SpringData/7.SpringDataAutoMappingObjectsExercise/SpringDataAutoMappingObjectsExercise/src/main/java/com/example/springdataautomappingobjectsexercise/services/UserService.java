package com.example.springdataautomappingobjectsexercise.services;


import com.example.springdataautomappingobjectsexercise.models.entities.User;

public interface UserService {
    User login();
    void register();
    void logout();

    User getUser();

    void printOwnedGames();

    void save(User user);
}
