package com.example.springdataautomappingobjectsexercise.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_game",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"))
    private List<Game> ownedGames;
    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;

    protected User() {
        this.ownedGames = new ArrayList<>();
        this.isAdmin = false;
    }

    private User(String email, String password, String fullName) {
        this();
        setEmail(email);
        setPassword(password);
        setFullName(fullName);
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email must not be null or empty!");
        }
        if (!email.matches(
                "^(?:[a-zA-Z0-9]+(?:[._-][a-zA-Z0-9]+)*)@(?:[a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*\\.[a-zA-Z]{2,})$")) {
            throw new IllegalArgumentException("Invalid email format for: " + email);
        }
        this.email = email;
    }

    private String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password should not be null or empty!");
        }

        if (!password.matches(
                "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*?[!@#$%^&*()_+<>?])[A-Za-z\\d!@#$%^&*()_+<>?]{6,50}$")) {
            throw new IllegalArgumentException(
                    "Passowrd length should contain at least " +
                            "a lower case letter, a upper case letter, special symbol and a number! " +
                            "Passowrd length Should be between 6 and 50 symbols! For password: " + password);
        }

        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    private void setFullName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Full name should not be null or blank!");
        }
        this.fullName = fullName;
    }

    public List<Game> getOwnedGames() {
        return Collections.unmodifiableList(ownedGames);
    }

    public void addGameToOwned(Game game) {
        ownedGames.add(game);
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    private void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    private boolean checkIfOwn(Game game) {
        return ownedGames.contains(game);
    }

    public static UserBuilder getBuilder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private final User user;
        private String email;
        private String password;
        private String fullName;

        private UserBuilder() {
            user = new User();
        }

        public String getEmail() {
            return email;
        }

        public UserBuilder setEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public String getPassword() {
            return user.getPassword();
        }

        public UserBuilder setPassword(String password, String confirmPassword) {
            if (!password.equals(confirmPassword)) {
                throw new IllegalArgumentException("Confirm password must match password!");
            }
            user.setPassword(password);
            return this;
        }

        public String getFullName() {
            return fullName;
        }

        public UserBuilder setFullName(String fullName) {
            if (fullName == null || fullName.isBlank()) {
                throw new IllegalArgumentException("Full name should not be null or blank!");
            }
            this.fullName = fullName;
            return this;
        }

        public User build() {
            if (this.password == null) {
                throw new IllegalArgumentException("Password must not be null!");
            }
            if (this.email == null) {
                throw new IllegalArgumentException("Email must not be null!");
            }

            if (this.fullName == null) {
                throw new IllegalArgumentException("Full name must not be null!");
            }

            return new User(this.email, this.password, this.fullName);
        }
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof User)) return false;
//
//        User user = (User) o;
//
//        if (!getId().equals(user.getId())) return false;
//        return getEmail().equals(user.getEmail());
//    }
//
//    @Override
//    public int hashCode() {
//        int result = getId().hashCode();
//        result = 31 * result + getEmail().hashCode();
//        return result;
//    }
}
