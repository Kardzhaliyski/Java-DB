package com.example.springdataautomappingobjects.services;

import com.example.springdataautomappingobjects.models.City;

import java.util.List;


public interface CityService {
    void save(City city);
    List<City> getAll();
}
