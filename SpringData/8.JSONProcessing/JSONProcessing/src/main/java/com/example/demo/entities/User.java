package com.example.demo.entities;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column
    private Integer age;

    @ManyToMany()
    private final Set<User> friends;

    protected User() {
        friends = new HashSet<>();
    }

    public User(String lastName) {
        this();
        this.lastName = lastName;
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
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    private void setAge(Integer age) {
        this.age = age;
    }

    public Set<User> getFriends() {
        return Collections.unmodifiableSet(friends);
    }

    public void addFriend(User friend) {
        friends.add(friend);
    }
}
