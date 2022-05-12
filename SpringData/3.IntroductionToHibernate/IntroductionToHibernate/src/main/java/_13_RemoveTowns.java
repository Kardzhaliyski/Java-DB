import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class _13_RemoveTowns {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();


        String townName = new Scanner(System.in).nextLine();

        Town town = entityManager.createQuery("SELECT t FROM Town t WHERE t.name = :name", Town.class)
                .setParameter("name", townName)
                .getSingleResult();
        List<Address> addresses = entityManager.createQuery("SELECT a FROM Address a WHERE a.town = :town", Address.class)
                .setParameter("town", town).getResultList();
        List<Integer> addressesIds = addresses.stream().map(Address::getId).collect(Collectors.toList());
        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e WHERE e.address.id IN (:ids)", Employee.class)
                .setParameter("ids", addressesIds)
                .getResultList();

        employees.forEach(e -> e.setAddress(null));
        addresses.forEach(a -> a.setTown(null));

        int numberOfAddresses = addresses.size();

        addresses.forEach(entityManager::remove);
        entityManager.remove(town);

        System.out.printf("%d %s in %s deleted",
                numberOfAddresses,
                numberOfAddresses == 1 ? "address" : "addresses",
                townName);



        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
