package com.example.springdataautomappingobjects.services;

import com.example.springdataautomappingobjects.models.Employee;
import com.example.springdataautomappingobjects.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public void save(Employee employee) {
        this.employeeRepository.save(employee);
    }
}
