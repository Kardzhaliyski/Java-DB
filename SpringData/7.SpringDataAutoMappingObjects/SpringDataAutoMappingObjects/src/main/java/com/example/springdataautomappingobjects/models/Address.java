package com.example.springdataautomappingobjects.models;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private City city;
    private String street;

    protected Address() {
    }

    public Address(City city, String street) {
        this.city = city;
        this.street = street;
    }

    public Long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    private void setCity(City city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    private void setStreet(String street) {
        this.street = street;
    }
}
