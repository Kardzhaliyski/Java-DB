import entities.Employee;

import javax.persistence.*;
import java.util.Scanner;

public class _03_ContainsEmployee {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();


        Scanner sc = new Scanner(System.in);
        String firstName = sc.nextLine();
        String lastName = sc.nextLine();
//        try {
//            Employee employee = entityManager.createQuery("FROM Employee e" +
//                                    " WHERE e.firstName = :firstName" +
//                                    " AND e.lastName = :lastName",
//                            Employee.class)
//                    .setParameter("firstName", firstName)
//                    .setParameter("lastName", lastName)
//                    .getSingleResult();
//            System.out.println("Yes");
//        } catch (NoResultException e) {
//            System.out.println("No");
//        }

        Long employeeCount = entityManager.createQuery("SELECT count(e.id) FROM Employee e" +
                                " WHERE e.firstName = :firstName" +
                                " AND e.lastName = :lastName",
                        Long.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getSingleResult();

//        if(employeeCount > 0) {
//            System.out.println("Yes");
//        } else {
//            System.out.println("No");
//        }

        System.out.println(employeeCount > 0 ? "Yes" : "No");

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
