import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class _02_ChangeCasing {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT t FROM Town t", Town.class);
        List<Town> towns = query.getResultList();

        for (Town town : towns) {
            if(town.getName().length() > 5) {
                entityManager.detach(town);
            }
            town.setName(town.getName().toUpperCase());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
