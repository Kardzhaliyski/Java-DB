import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args )  {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-relate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        PlateNumber plateNumber = new PlateNumber("12534d");
        Vehicle vehicle = new Car("f13", BigDecimal.valueOf(375.52), "Oil", 5, plateNumber);

        entityManager.persist(plateNumber);
        entityManager.persist(vehicle);

        

        entityManager.getTransaction().commit();
        entityManager.close();


    }
}