package sales.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id",nullable = false)
    private Product product;


    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id",nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "store_location_id", referencedColumnName = "id",nullable = false)
    private StoreLocation storeLocation; 
    @Column
    LocalDate date;

    protected Sale() {
    }

    public Sale(Product product, Customer customer, StoreLocation storeLocation, LocalDate date) {
        this.product = product;
        this.customer = customer;
        this.storeLocation = storeLocation;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    private void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    private void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public StoreLocation getStoreLocation() {
        return storeLocation;
    }

    private void setStoreLocation(StoreLocation storeLocation) {
        this.storeLocation = storeLocation;
    }

    public LocalDate getDate() {
        return date;
    }

    private void setDate(LocalDate date) {
        this.date = date;
    }
}
