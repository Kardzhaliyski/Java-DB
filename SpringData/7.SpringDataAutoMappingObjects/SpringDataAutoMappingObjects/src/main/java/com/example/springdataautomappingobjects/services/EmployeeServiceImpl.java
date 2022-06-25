package com.example.springdataautomappingobjects.services;

import com.example.springdataautomappingobjects.models.Employee;
import com.example.springdataautomappingobjects.models.EmployeeDTO;
import com.example.springdataautomappingobjects.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Override
    public EmployeeDTO convert(Employee employee) {
        ModelMapper modelMapper = new ModelMapper();
        EmployeeDTO dto = modelMapper.map(employee, EmployeeDTO.class);
        return dto;
    }

    @Override
    public Optional<Employee> getById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<EmployeeDTO> getEmployeesWithBirthdayBefore(int year) {
        return employeeRepository.findEmployeesByBirthdayBeforeOrderBySalaryDesc(LocalDate.of(year, 1, 1));
    }
}
