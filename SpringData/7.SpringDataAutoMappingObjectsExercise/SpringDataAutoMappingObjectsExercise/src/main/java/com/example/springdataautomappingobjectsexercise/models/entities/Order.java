package com.example.springdataautomappingobjectsexercise.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private User user;
    @ManyToMany
    private Map<Long, Game> games;
    @Column(nullable = false)
    private Boolean finished;

    protected Order() {
    }

    public Order(User user) {
        this.user = user;
        this.games = new LinkedHashMap<>();
        this.finished = false;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    private void setUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User should not be null!");
        }
        this.user = user;
    }

    public Map<Long, Game> getGames() {
        return Collections.unmodifiableMap(games);
    }

    public void addGame(Game game) {
        if (game == null) {
            throw new IllegalArgumentException("Game must not be null!");
        }

        if (games.containsKey(game.getId())) {
            throw new IllegalArgumentException("Game is already in the order list!");
        }

        games.put(game.getId(), game);
    }
    public void removeGame(Long id) {
        games.remove(id);
     }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished() {
        this.finished = true;
    }

    public BigDecimal getTotalPrice() {
        return this.games.values().stream().map(Game::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
}
