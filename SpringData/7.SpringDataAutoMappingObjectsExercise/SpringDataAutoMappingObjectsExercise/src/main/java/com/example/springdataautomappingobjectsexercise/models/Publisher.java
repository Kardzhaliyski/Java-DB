package com.example.springdataautomappingobjectsexercise.models;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "pubishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
        this.name = name;
    }

    public Set<Game> getGames() {
        return Collections.unmodifiableSet(this.games);
    }

    private void setGames(Set<Game> games) {
        this.games = games;
    }
}
