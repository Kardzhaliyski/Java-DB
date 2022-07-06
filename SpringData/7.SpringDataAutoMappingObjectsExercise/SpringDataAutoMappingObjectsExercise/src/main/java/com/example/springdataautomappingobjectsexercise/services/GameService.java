package com.example.springdataautomappingobjectsexercise.services;

import com.example.springdataautomappingobjectsexercise.models.entities.Game;
import com.example.springdataautomappingobjectsexercise.models.entities.Publisher;

public interface GameService {
    boolean existInDatabase(Game game);
    boolean existInDatabase(String name, Publisher publisher);
    void save(Game game);

    void addGame();
    void printAllGame();

    Game getGame(Long id);
    String readTitle();
}
