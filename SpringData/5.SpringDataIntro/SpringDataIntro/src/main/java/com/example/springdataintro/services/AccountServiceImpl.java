package com.example.springdataintro.services;


import com.example.springdataintro.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springdataintro.repositories.AccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal withdrawAmount, Account account) {
        if (withdrawAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Amount to withdraw should be positive number bigger then 0 for amount: " + withdrawAmount);
        }

        if (account == null) {
            throw new NullPointerException("Account should not be null!");
        }

        if (account.getId() == null) {
            throw new IllegalStateException("Account should be persisted to the database!");
        }

        if (account.getUser() == null) {
            throw new NullPointerException("For User with account id: " + account.getId());
        }

        BigDecimal balance = account.getBalance();

        if (balance.compareTo(withdrawAmount) < 0) {
            throw new IllegalStateException(
                    "Not enough balance for withdraw operation for Account id: " + account.getId());
        }

        account.setBalance(balance.subtract(withdrawAmount));
        accountRepository.save(account);
    }

    @Override
    public void depositMoney(BigDecimal depositAmount, Account account) {
        if (depositAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Amount to deposit should be positive number bigger then 0 for amount: " + depositAmount);
        }

        if (account == null) {
            throw new NullPointerException("Account should not be null!");
        }

        if (account.getId() == null) {
            throw new IllegalStateException("Account should be persisted to the database!");
        }

        if (account.getUser() == null) {
            throw new NullPointerException("For User with account id: " + account.getId());
        }


        account.setBalance(account.getBalance().add(depositAmount));
        accountRepository.save(account);
    }

    @Override
    public void registerAccount(Account account) {
        if (account == null) {
            throw new IllegalStateException("User should not be null");
        }

        if (account.getId() != null && accountRepository.existsById(account.getId())) {
            return;
        }

        accountRepository.save(account);
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findAccountById(id);
    }
}
