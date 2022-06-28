package com.example.springdataautomappingobjectsexercise.services;

import org.springframework.stereotype.Service;

import java.util.Scanner;
@Service
public class ScannerReader implements ReaderService {
    private final Scanner scanner;
    protected ScannerReader(){
        this.scanner = new Scanner(System.in);
    }


    @Override
    public String nextLine() {
       return scanner.nextLine();
    }

}
