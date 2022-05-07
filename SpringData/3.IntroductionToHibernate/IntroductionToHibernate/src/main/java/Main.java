import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();
        List<Employee> employees = entityManager.createQuery("SELECT a FROM Employee a").getResultList();
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
        entityManager.getTransaction().commit();
    }
}
