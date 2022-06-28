package com.example.springdataautomappingobjectsexercise;

import com.example.springdataautomappingobjectsexercise.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        User.Builder builder = User.getBuilder();
        Scanner sc = new Scanner(System.in);

        while (builder.getEmail() == null) {
            try {
                System.out.print("Enter Email address: ");
                builder.setEmail(sc.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
