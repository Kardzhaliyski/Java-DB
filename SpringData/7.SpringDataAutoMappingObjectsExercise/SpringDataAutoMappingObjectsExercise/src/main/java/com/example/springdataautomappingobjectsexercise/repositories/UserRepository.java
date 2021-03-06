package com.example.springdataautomappingobjectsexercise.repositories;

import com.example.springdataautomappingobjectsexercise.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);
    User findUserByEmailAndPassword(String email, String password);
}
