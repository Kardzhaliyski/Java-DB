package com.example.springdataautomappingobjectsexercise.repositories;

import com.example.springdataautomappingobjectsexercise.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
