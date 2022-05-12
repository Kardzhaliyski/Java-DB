import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class _12_EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Object[]> departmentsAndMaxSalary = entityManager.createQuery(
                "SELECT e.department.name, max(e.salary) " +
                        " FROM Employee e" +
                        " GROUP BY e.department" +
                        " HAVING max(e.salary) NOT BETWEEN 30000 AND 70000", Object[].class)
                .getResultList();

        for (Object[] department : departmentsAndMaxSalary) {
            Object departmentName = department[0];
            Object salary = department[1];

            System.out.printf("%s %.2f\n", departmentName, salary);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
