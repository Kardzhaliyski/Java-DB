import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class _07_AdressesWithEmployeeCount {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        List<Address> addresses = entityManager.createQuery("SELECT a FROM Address a" +
                                " ORDER BY a.employees.size DESC",
                        Address.class)
                .setMaxResults(10)
                .getResultList();

        StringBuilder sb = new StringBuilder();
        for (Address address : addresses) {
            sb.append(String.format("%s, %s - %s employees",
                    address.getText(),
                    address.getTown() == null ? "" : address.getTown().getName(),
                    address.getEmployees().size()))
                    .append(System.lineSeparator());
        }

        System.out.println(sb);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
