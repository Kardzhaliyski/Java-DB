package com.example.springdataautomappingobjects;

import com.example.springdataautomappingobjects.models.Address;
import com.example.springdataautomappingobjects.models.City;
import com.example.springdataautomappingobjects.services.AddressService;
import com.example.springdataautomappingobjects.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class Runner implements CommandLineRunner {
    private final CityService cityService;
    private final AddressService addressService;

    @Autowired
    public Runner(CityService cityService, AddressService addressService) {
        this.cityService = cityService;
        this.addressService = addressService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<City> cities = cityService.getAll();
        for (int i = 0; i < cities.size(); i++) {
            for (int j = 0; j < 12; j++) {
                Address address = new Address(cities.get(i), Character.toString('a' + i) + j);
                addressService.save(address);
            }
        }

//        Address address = new Address(cities.get(0), "a99");
//        addressService.save(address);

    }
}
