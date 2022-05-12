import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _08_GetEmployeeWithProject {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();

        Employee employee = entityManager.find(Employee.class, id);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s - %s",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJobTitle()));

        employee.getProjects().stream()
                .map(Project::getName)
                .sorted()
                .forEach(n -> sb.append(System.lineSeparator()).append("    ").append(n));

        System.out.println(sb);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
