package com.example.springdataautomappingobjectsexercise.models.entities;

import javax.persistence.*;
import java.util.*;

@Entity(name = "users")
public class User {
    public static class Builder {
        private final User user;

        private Builder() {
            user = new User();
        }

        public String getEmail() {
            return user.getEmail();
        }

        public Builder setEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public String getPassword() {
            return user.getPassword();
        }

        public Builder setPassword(String password, String confirmPassword) {
            if (!password.equals(confirmPassword)) {
                throw new IllegalArgumentException("Confirm password must match password!");
            }
            user.setPassword(password);
            return this;
        }

        public String getFullName() {
            return user.getFullName();
        }

        public Builder setFullName(String fullName) {
            user.setFullName(fullName);
            return this;
        }

        public User build() {
            if (user.password == null) {
                throw new IllegalStateException("Password must not be null!");
            }
            if (user.email == null) {
                throw new IllegalStateException("Email must not be null!");
            }

            if (user.fullName == null) {
                throw new IllegalStateException("Full name must not be null!");
            }

            return user;
        }
    }


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
    private final Map<Long, Game> ownedGames;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;

    protected User() {
        this.ownedGames = new LinkedHashMap<>();
        this.isAdmin = false;
    }

    public static Builder getBuilder() {
        return new Builder();
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
        this.fullName = fullName.trim();
    }

    public Map<Long, Game> getOwnedGames() {
        return Collections.unmodifiableMap(ownedGames);
    }

    public void addGameToOwned(Game game) {
        ownedGames.put(game.getId(), game);
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    private void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User: " + fullName + " Email: " + email;
    }

    public boolean ownGame(Long id) {
        return ownedGames.containsKey(id);
    }

}
