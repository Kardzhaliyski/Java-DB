package com.example.springdataautomappingobjectsexercise.models.entities;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "pubishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "publisher")
    private Set<Game> games;

    protected Publisher() {
    }

    public Publisher(String name) {
        this.name = name;
        this.games = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Publisher name should not be Null or empty!");
        }
        this.name = name;
    }

    public Set<Game> getGames() {
        return Collections.unmodifiableSet(this.games);
    }

    public void addGame(Game game) {
        this.games.add(game);
    }
}
