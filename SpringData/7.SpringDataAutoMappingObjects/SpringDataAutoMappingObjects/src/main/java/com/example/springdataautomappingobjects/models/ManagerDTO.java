package com.example.springdataautomappingobjects.models;

import java.util.List;
import java.util.stream.Collectors;

public class ManagerDTO {
    private String firstName;
    private String lastName;
    private List<EmployeeDTO> subordinates;

    public ManagerDTO() {
    }

    public ManagerDTO(String firstName, String lastName, List<EmployeeDTO> subordinates) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subordinates = subordinates;


    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<EmployeeDTO> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<EmployeeDTO> subordinates) {
        this.subordinates = subordinates;
    }

    @Override
    public String toString() {
        return String.format("%s %s | Employees: %d%s",
                firstName,
                lastName,
                subordinates.size(),
                subordinates.stream()
                        .map(s -> String.format("%n - %s", s.toString()))
                        .collect(Collectors.joining(""))
        );

    }
}
