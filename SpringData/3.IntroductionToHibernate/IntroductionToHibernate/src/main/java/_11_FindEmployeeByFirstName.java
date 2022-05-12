import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class _11_FindEmployeeByFirstName {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner sc = new Scanner(System.in);

        String pattern = sc.nextLine() + "%";
        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e WHERE e.firstName LIKE :pattern", Employee.class)
                .setParameter("pattern", pattern)
                .getResultList();

        for (Employee employee : employees) {
            System.out.printf("%s %s - %s - ($%.2f)\n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getJobTitle(),
                    employee.getSalary());
        }


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
