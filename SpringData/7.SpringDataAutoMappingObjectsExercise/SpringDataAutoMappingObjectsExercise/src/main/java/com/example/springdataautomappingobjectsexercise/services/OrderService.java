package com.example.springdataautomappingobjectsexercise.services;

import com.example.springdataautomappingobjectsexercise.models.entities.Game;
import com.example.springdataautomappingobjectsexercise.models.entities.User;

public interface OrderService {
    void startOrder(User user);

    void addGame(Game game);

    void removeGame(Long id);

    void printCart();

    void finishOrder();
}
