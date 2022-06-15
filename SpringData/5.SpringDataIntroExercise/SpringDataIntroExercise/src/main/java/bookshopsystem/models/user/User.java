package bookshopsystem.models.user;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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
    @ManyToOne
    private Town townOfBirth;
    @ManyToOne
    private Town townOfResidance;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Transient
    private String fullName;
    @ManyToMany
    private Set<User> friends;
    @OneToMany
    private Set<Album> albums;
    @OneToMany
    private Set<Picture> pictures;

    protected User() {
        friends = new HashSet<>();
    }

    public User(String username, String password, String email, int age) {
        this();
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setAge(age);
        setRegistrationDate(LocalDate.now());
        setLastLoginDateTime(LocalDateTime.now());
        setDeleted(false);
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        if (!username.matches("^[A-Za-z+]+$")) {
            throw new IllegalArgumentException("Username should contains only letters! For username: " + username);
        }

        if (username.length() < 4 || username.length() > 30) {
            throw new IllegalArgumentException("Username should be between 4 and 30 symbols! For username: " + username);
        }

        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        if (password.length() < 6 || password.length() > 50) {
            throw new IllegalArgumentException("Password length must be between 6 and 50 symbols! For password: " + password);
        }
        String passwordValidatingRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*?[!@#$%^&*()_+<>?])[A-Za-z\\d!@#$%^&*()_+<>?]{6,50}$";
        if (!password.matches(passwordValidatingRegex)) {
            throw new IllegalArgumentException("Password must contain at least 1 Upper case letter, 1 Lower case letter, 1 Number , 1 Special symbol(!, @, #, $, %, ^, &, *, (, ), _, +, <, >, ?)! For password: " + password);
        }
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        String emailValidatingRegex = "^(?:[a-zA-Z0-9]+(?:[._-][a-zA-Z0-9]+)*)@(?:[a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*\\.[a-zA-Z]{2,})$";
        if (!email.matches(emailValidatingRegex)) {
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
        if (age < 1 || age > 120) {
            throw new IllegalArgumentException("Age should be in the range between 1 and 120! For age: " + age);
        }
        this.age = age;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    private void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Town getTownOfBirth() {
        return townOfBirth;
    }

    private void setTownOfBirth(Town townOfBirth) {
        this.townOfBirth = townOfBirth;
    }

    public Town getTownOfResidance() {
        return townOfResidance;
    }

    private void setTownOfResidance(Town townOfResidance) {
        this.townOfResidance = townOfResidance;
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

    public String getFullName() {
        if(this.fullName == null){
            String firstName = this.firstName == null ? "" : this.firstName;
            String lastName = this.lastName == null ? "" : this.lastName;
            this.fullName = (firstName + " " + lastName).trim().length() == 0 ? null : (firstName + " " + lastName).trim();
        }
        return fullName;
    }


    public Set<User> getFriends() {
        return Collections.unmodifiableSet(this.friends);
    }

    private void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<Album> getAlbums() {
        return  Collections.unmodifiableSet(this.albums);
    }

    private void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Set<Picture> getPictures() {
        return Collections.unmodifiableSet(pictures);
    }

    private void setPictures() {
        pictures = new HashSet<>();
    }

    private void addPicture(Picture picture) {
        pictures.add(picture);
    }
}
