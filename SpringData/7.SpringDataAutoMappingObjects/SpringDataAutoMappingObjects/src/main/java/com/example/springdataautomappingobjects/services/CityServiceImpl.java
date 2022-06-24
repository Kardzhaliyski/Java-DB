package com.example.springdataautomappingobjects.services;

import com.example.springdataautomappingobjects.models.City;
import com.example.springdataautomappingobjects.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public void save(City city) {
        cityRepository.save(city);
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }
}
