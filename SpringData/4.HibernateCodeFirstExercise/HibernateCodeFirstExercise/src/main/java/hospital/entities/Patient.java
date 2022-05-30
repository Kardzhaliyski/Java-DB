package hospital.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String address;
    private String email;
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    private String picture;
    @Column(nullable = false)
    private boolean insured;
    @OneToMany(mappedBy = "patient")
    private List<Visit> visits;

    protected Patient() {
    }

    public Patient(String firstName, String lastName, String address, String email, LocalDate dateOfBirth, boolean insured) {
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setEmail(email);
        setDateOfBirth(dateOfBirth);
        this.insured = insured;
        this.visits = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        if(firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("Patient name must not be Null or Empty!");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        if(lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Patient name must not be Null or Empty!");
        }
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    private void setAddress(String address) {
        if(address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address must not be Null or Empty!");
        }
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        if(email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email must not be Null or Empty!");
        }
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    private void setDateOfBirth(LocalDate dateOfBirth) {
        if(dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth must be valid!");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public String getPicture() {
        return picture;
    }

    private void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isInsured() {
        return insured;
    }

    public void changeInsurenceStatus() {
        this.insured = !this.insured;
    }

    public void addVisit(Visit visit) {
        if (!visits.contains(visit)) {
            visits.add(visit);
        }
    }
}
