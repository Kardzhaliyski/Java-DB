import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class _04_EmployeesWithSalaryOver50000 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<String> employeeNames = entityManager.createQuery("SELECT e.firstName" +
                        " FROM Employee e" +
                        " WHERE e.salary > 50000", String.class)
                .getResultList();

        for (String employeeName : employeeNames) {
            System.out.println(employeeName);
        }


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}