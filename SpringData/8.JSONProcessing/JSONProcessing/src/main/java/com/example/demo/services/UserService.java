package com.example.demo.services;

import com.example.demo.entities.users.User;
import com.example.demo.entities.users.UserWithSellsDTO;

import java.util.List;

public interface UserService {
    void save(User user);
    User getRandomUser();
    List<UserWithSellsDTO> getAllUsersWithSells();
}
