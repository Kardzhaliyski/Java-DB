package com.example.springdataautomappingobjectsexercise.repositories;

import com.example.springdataautomappingobjectsexercise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
