package com.example.springdataintro.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal balance;
    @ManyToOne(optional = false)
    private User user;

    protected Account() {
    }

    public Account(BigDecimal balance, User user) {
        setBalance(balance);
        setUser(user);
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        if(balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Balance can't be less then 0. For balance: " + balance);
        }
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if(user == null) {
            throw new IllegalArgumentException("User should not be null!");
        }

        this.user = user;
    }
}
