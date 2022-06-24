package com.example.springdataautomappingobjects.services;

import com.example.springdataautomappingobjects.models.Address;
import com.example.springdataautomappingobjects.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }
}
