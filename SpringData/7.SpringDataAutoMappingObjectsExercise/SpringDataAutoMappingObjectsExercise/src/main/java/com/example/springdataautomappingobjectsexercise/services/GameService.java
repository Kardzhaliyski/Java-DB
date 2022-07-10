package com.example.springdataautomappingobjectsexercise.services;

import com.example.springdataautomappingobjectsexercise.models.entities.Game;
import com.example.springdataautomappingobjectsexercise.models.entities.Publisher;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface GameService {
    boolean existInDatabase(Game game);
    boolean existInDatabase(String name, Publisher publisher);
    void save(Game game);

    void addGame();
    void printAllGame();

    Game getGame(Long id);
    String readTitle();

    String readTrailerUrlId();

    String readThumbnailUrl();

    Double readGameSize();

    BigDecimal readPrice();

    String readDescription();

    LocalDate readReleaseDate();
}
