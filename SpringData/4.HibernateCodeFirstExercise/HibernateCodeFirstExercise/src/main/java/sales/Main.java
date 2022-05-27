package sales;

import sales.entities.Customer;
import sales.entities.Product;
import sales.entities.Sale;
import sales.entities.StoreLocation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = new Product("Chocolate", 12.5, BigDecimal.valueOf(10.5));
        Customer customer = new Customer("Gosho", "Gosho@mail.com", "3338885792");
        StoreLocation location = new StoreLocation("Plovdiv");
        Sale sale = new Sale(product, customer, location, LocalDate.now());

        entityManager.persist(product);
        entityManager.persist(customer);
        entityManager.persist(location);
        entityManager.persist(sale);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
