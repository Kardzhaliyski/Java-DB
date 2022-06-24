package com.example.springdataautomappingobjects.services;

import com.example.springdataautomappingobjects.models.Address;

import java.util.List;

public interface AddressService {
    void save(Address address);

    List<Address> getAll();
}
