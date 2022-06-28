package com.example.springdataautomappingobjectsexercise.models;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private User user;
    @ManyToMany
    private Set<Game> games;
    @Column(nullable = false)
    private Boolean finished;

    protected Order() {
    }

    public Order(User user) {
        this.user = user;
        this.games = new LinkedHashSet<>();
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

    public Set<Game> getGames() {
        return Collections.unmodifiableSet(games);
    }

    public void addGame(Game game) {
        if (game == null) {
            throw new IllegalArgumentException("Game must not be null!");
        }

        if (games.contains(game)) {
            throw new IllegalArgumentException("Game is already in the order list!");
        }

        games.add(game);
    }

    public Boolean getFinished() {
        return finished;
    }

    private void setFinished() {
        this.finished = true;
    }
}
