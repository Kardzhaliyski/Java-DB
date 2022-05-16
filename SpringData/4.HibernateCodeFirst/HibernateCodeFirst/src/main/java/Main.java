import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-relate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        PlateNumber plateNumber = new PlateNumber("number12341");
        Car vehicle = new Car("Balkanche", BigDecimal.valueOf(1300),  "Electricity",3, plateNumber);
        plateNumber.setCar(vehicle);
        entityManager.persist(plateNumber);
        entityManager.persist(vehicle);


        entityManager.getTransaction().commit();
        entityManager.close();


    }
}