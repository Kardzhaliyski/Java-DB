package com.example.demo.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;
    @ManyToMany
    private Set<Category> categories;
    @ManyToOne(optional = false)
    private User seller;
    @ManyToOne
    private User buyer;

    protected Product() {
        categories = new HashSet<>();
    }

    public Product(Long id, String name, BigDecimal price, Set<Category> categories, User seller, User buyer) {
        this();
        this.id = id;
        this.name = name;
        this.price = price;
        this.categories = categories;
        this.seller = seller;
        this.buyer = buyer;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    private void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public void addCategories(Set<Category> categories) {
        this.categories.addAll(categories);
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
}
