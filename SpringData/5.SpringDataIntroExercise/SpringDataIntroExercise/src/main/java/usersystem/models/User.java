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

}
