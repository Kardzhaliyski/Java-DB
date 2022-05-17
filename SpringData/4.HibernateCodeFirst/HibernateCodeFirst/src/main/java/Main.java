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

        Company company = new Company("Tesla");
        Plane plane = new Plane("f13", BigDecimal.valueOf(375.52), "Oil", "Fonta", 735);
        plane.setCompany(company);
        entityManager.persist(company);
        entityManager.persist(plane);

        entityManager.getTransaction().commit();
        entityManager.close();


    }
}