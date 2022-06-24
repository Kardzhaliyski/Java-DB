package com.example.springdataautomappingobjects.repositories;

import com.example.springdataautomappingobjects.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
