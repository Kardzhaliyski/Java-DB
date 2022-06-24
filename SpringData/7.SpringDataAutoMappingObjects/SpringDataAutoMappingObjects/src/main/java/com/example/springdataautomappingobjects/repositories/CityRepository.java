package com.example.springdataautomappingobjects.repositories;

import com.example.springdataautomappingobjects.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
