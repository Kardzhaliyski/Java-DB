package com.example.springdataintro.services;

import com.example.springdataintro.models.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public interface AccountService {
    void withdrawMoney(BigDecimal money, Account account);
    void depositMoney(BigDecimal money, Account account);
    void registerAccount(Account account);
    Account getAccountById(Long id);
}
