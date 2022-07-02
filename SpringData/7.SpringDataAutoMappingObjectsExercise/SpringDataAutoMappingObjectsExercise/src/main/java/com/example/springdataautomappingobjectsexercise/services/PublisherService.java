package com.example.springdataautomappingobjectsexercise.services;

import com.example.springdataautomappingobjectsexercise.models.entities.Publisher;

public interface PublisherService {
    Boolean existsByName(String name);

    Boolean exists(Publisher publisher);

    Publisher getByName(String name);

    void save(Publisher publisher);
}
