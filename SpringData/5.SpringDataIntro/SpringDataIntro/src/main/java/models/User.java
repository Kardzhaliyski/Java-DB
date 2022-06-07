package models;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private int age;

    @OneToMany(mappedBy = "user")
    private Set<Account> accoounts;

    protected User() {
        this.accoounts = new HashSet<>();
    }

    public User(String username, int age) {
        this.username = username;
        this.age = age;
        this.accoounts = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public Set<Account> getAccoounts() {
        return Collections.unmodifiableSet(accoounts);
    }

    public void addAccount(Account account) {
        this.accoounts.add(account);
    }
}
