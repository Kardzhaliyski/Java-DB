package com.example.demo.entities.users;

import com.example.demo.entities.products.SoldProductsDTO;

import java.util.List;

public class UserWithSellsDTO {
    private String firstName;
    private String lastName;
    private List<SoldProductsDTO> soldProducts;

    public UserWithSellsDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<SoldProductsDTO> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<SoldProductsDTO> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
