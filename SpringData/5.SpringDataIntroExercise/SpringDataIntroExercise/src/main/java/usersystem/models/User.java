package usersystem.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, length = 50)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(name = "registered_on")
    private LocalDate registrationDate;
    @Column(name = "last_time_logged_in")
    private LocalDateTime lastLoginDateTime;
    @Column
    private int age;
    @Column(name = "is_deleted")
    private boolean isDeleted;

    protected User() {
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        if(!username.matches("^[A-Za-z+]+$")) {
            throw new IllegalArgumentException("Username should contains only letters! For username: " + username);
        }

        if(username.length() < 4 || username.length() > 30) {
            throw new IllegalArgumentException("Username should be between 4 and 30 symbols! For username: " + username);
        }

        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        if(password.length() < 6 || password.length() > 50) {
            throw new IllegalArgumentException("Password length must be between 6 and 50 symbols! For password: " + password);
        }
        String passwordValidatingRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*?[!@#$%^&*()_+<>?])[A-Za-z\\d!@#$%^&*()_+<>?]{6,50}$";
        if(!password.matches(passwordValidatingRegex)) {
            throw new IllegalArgumentException("Password must contain at least 1 Upper case letter, 1 Lower case letter, 1 Number , 1 Special symbol(!, @, #, $, %, ^, &, *, (, ), _, +, <, >, ?)! For password: " + password);
        }
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        String emailValidatingRegex = "^(?:[a-zA-Z0-9]+(?:[._-][a-zA-Z0-9]+)*)@(?:[a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*\\.[a-zA-Z]{2,})$";
        if(!email.matches(emailValidatingRegex)) {
            throw new IllegalArgumentException("Invalid email! For: " + email);
        }

        this.email = email;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    private void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDateTime getLastLoginDateTime() {
        return lastLoginDateTime;
    }

    private void setLastLoginDateTime(LocalDateTime lastLoginDateTime) {
        this.lastLoginDateTime = lastLoginDateTime;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    private void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
