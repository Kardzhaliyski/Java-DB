package com.example.springdataautomappingobjects.repositories;

import com.example.springdataautomappingobjects.models.Employee;
import com.example.springdataautomappingobjects.models.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select" +
            " new com.example.springdataautomappingobjects.models.EmployeeDTO(e.firstName, e.lastName, e.salary, e.manager.lastName)" +
            " from Employee e" +
            " where e.birthday < ?1" +
            " order by e.salary DESC")
    List<EmployeeDTO> findEmployeesByBirthdayBeforeOrderBySalaryDesc(LocalDate date);
}
