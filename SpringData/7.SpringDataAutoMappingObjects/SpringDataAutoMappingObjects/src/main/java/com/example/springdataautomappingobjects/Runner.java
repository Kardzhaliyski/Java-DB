package com.example.springdataautomappingobjects;

import com.example.springdataautomappingobjects.models.Address;
import com.example.springdataautomappingobjects.models.Employee;
import com.example.springdataautomappingobjects.services.AddressService;
import com.example.springdataautomappingobjects.services.CityService;
import com.example.springdataautomappingobjects.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
public class Runner implements CommandLineRunner {
    private final CityService cityService;
    private final AddressService addressService;
    private final EmployeeService employeeService;
    private StringBuilder sb;
    private Random random;


    @Autowired
    public Runner(CityService cityService, AddressService addressService, EmployeeService employeeService) {
        this.cityService = cityService;
        this.addressService = addressService;
        this.employeeService = employeeService;
        this.sb = new StringBuilder();
        this.random = new Random();
    }


    @Override
    public void run(String... args) throws Exception {

    }

    private void generateRandomEmployees() {
        List<Address> addresses = addressService.getAll();

        for (int i = 0; i < 1000; i++) {

            String firstName = generateRandomString();
            String lastName = generateRandomString();
            Employee employee = new Employee(
                    firstName,
                    lastName,
                    BigDecimal.valueOf(random.nextInt(1000)),
                    LocalDate.ofYearDay(
                            random.nextInt(2023),
                            random.nextInt(365) + 1),
                    addresses.get(random.nextInt(addresses.size())));
            employeeService.save(employee);
        }
    }

    private String generateRandomString() {
        for (int i = 0; i < random.nextInt(5) + 3; i++) {
            sb.append((char) ('a' + random.nextInt(26)));
        }

        String randomString = sb.toString();
        sb.setLength(0);
        return randomString;
    }
}
