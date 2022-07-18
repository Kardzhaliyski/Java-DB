package com.example.demo.entities.products;

import java.math.BigDecimal;

public class ProductWithoutBuyerDTO {
    private final String name;
    private final BigDecimal price;
    private final String seller;

    public ProductWithoutBuyerDTO(String name, BigDecimal price, String sellerFirstName, String sellerLastName) {
        this.name = name;
        this.price = price;
        if (sellerFirstName == null) {
            this.seller = sellerLastName;
        } else {
            this.seller = sellerFirstName + " " + sellerLastName;
        }
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSeller() {
        return seller;
    }
}
