package com.example.springdataautomappingobjectsexercise.repositories;

import com.example.springdataautomappingobjectsexercise.models.entities.Game;
import com.example.springdataautomappingobjectsexercise.models.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Boolean existsByTitleAndPublisher(String title, Publisher publisher);
    @Transactional
    int deleteGameById(Long id);
}
