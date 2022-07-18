package com.example.demo.entities.users;

import com.example.demo.entities.products.Product;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
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
    @OneToMany(mappedBy = "seller")
    private List<Product> sellingProducts;
    @OneToMany(mappedBy = "buyer")
    private List<Product> productsBought;

    @ManyToMany()
    private final Set<User> friends;

    protected User() {
        friends = new HashSet<>();
        sellingProducts = new ArrayList<>();
        productsBought = new ArrayList<>();
    }


    public User(String firstName, String lastName, Integer age) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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

    public List<Product> getSoldProducts() {
        return sellingProducts.stream().filter(p -> p.getBuyer() != null).collect(Collectors.toList());
//        return soldProducts;
    }

    public List<Product> getSellingProducts() {
        return sellingProducts;
    }

    public void setSellingProducts(List<Product> sellingProducts) {
        this.sellingProducts = sellingProducts;
    }

    public List<Product> getProductsBought() {
        return productsBought;
    }

    public void setProductsBought(List<Product> productsBought) {
        this.productsBought = productsBought;
    }
}
