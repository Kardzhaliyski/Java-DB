package com.example.springdataintro;

import com.example.springdataintro.models.Account;
import com.example.springdataintro.models.User;
import com.example.springdataintro.services.AccountService;
import com.example.springdataintro.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
@Transactional
public class ConsoleRunner implements CommandLineRunner {

    private UserService userService;
    private AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
//        User user = new User("Pesho", 33);
//        userService.registerUser(user);
//        System.out.println();
//        Account account = new Account(BigDecimal.valueOf(32000), user);
//        user.addAccount(account);

//        accountService.registerAccount(account);

        Account account = accountService.getAccountById(1L);

        System.out.println();
        System.out.println("Initial balance: " + account.getBalance());

        accountService.withdrawMoney(BigDecimal.valueOf(1000), account);
        System.out.println("Balance after withdraw: " + account.getBalance());

        accountService.depositMoney(BigDecimal.valueOf(13000), account);
        System.out.println("Balance after deposit: " + account.getBalance());



    }
}
