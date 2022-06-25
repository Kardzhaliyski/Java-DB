package com.example.springdataautomappingobjects.services;

import com.example.springdataautomappingobjects.models.Employee;
import com.example.springdataautomappingobjects.models.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void save(Employee employee);

    EmployeeDTO convert(Employee employee);

    Optional<Employee> getById(Long id);

    List<EmployeeDTO> getEmployeesWithBirthdayBefore(int year);
}
