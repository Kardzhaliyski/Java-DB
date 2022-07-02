package com.example.springdataautomappingobjectsexercise.repositories;

import com.example.springdataautomappingobjectsexercise.models.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Boolean existsByName(String name);
    Publisher findPublisherByName(String name);
}
