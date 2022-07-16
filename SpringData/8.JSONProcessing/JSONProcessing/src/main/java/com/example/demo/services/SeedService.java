package com.example.demo.services;

import java.io.FileNotFoundException;

public interface SeedService {
    void seedUsers() throws FileNotFoundException;
    void seedCatorogies();
    void seedProducts();

    default void seedAll() throws FileNotFoundException {
        seedUsers();
        seedCatorogies();
        seedProducts();
    }
}
