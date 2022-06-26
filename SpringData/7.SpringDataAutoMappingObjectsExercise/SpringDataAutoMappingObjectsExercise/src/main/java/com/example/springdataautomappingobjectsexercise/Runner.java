package com.example.springdataautomappingobjectsexercise;

import com.example.springdataautomappingobjectsexercise.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        User user = new User("pesho@gmail.com","123asdBSD", "Gosho Peshev");
        System.out.println(user);
    }
}
