import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _06_AddingANewAddressAndUpdatingEmployee {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Address address = new Address();
        address.setText("Vitoshka 15");
        entityManager.persist(address);

        Scanner sc = new Scanner(System.in);
        String employeeLastName = sc.nextLine();

        entityManager.createQuery("UPDATE Employee e SET e.address = :address WHERE e.lastName = :last_name ")
                .setParameter("last_name", employeeLastName)
                .setParameter("address", address)
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
