import data.EmployeeInfo;
import entities.Department;
import entities.Employee;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class _10_IncreaseSalaries {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();


        List<String> departmentNames = new ArrayList<>();
        departmentNames.add("Engineering");
        departmentNames.add("Tool Design");
        departmentNames.add("Marketing");
        departmentNames.add("Information Services");



//        entityManager.createQuery(
//                        "UPDATE Employee e" +
//                                " SET e.salary = (e.salary * 1.12)" +
//                                " WHERE e.department.id in" +
//                                " (SELECT d.id FROM Department d WHERE d.name in :department_names)")
//                .setParameter("department_names", departmentNames)
//                .executeUpdate();


//        List<EmployeeInfo> employeesInfo = entityManager.createQuery(
//                        "SELECT NEW data.EmployeeInfo(e.firstName, e.lastName, e.department.name, e.salary)" +
//                                " FROM Employee e" +
//                                " WHERE e.department.name IN (:department_names)" +
//                                " ORDER BY e.salary ASC," +
//                                " e.id ASC", EmployeeInfo.class)
//                .setParameter("department_names", departmentNames)
//                .getResultList();

//        StringBuilder sb = new StringBuilder();

//        for (EmployeeInfo employee : employeesInfo) {
//            sb.append(String.format("%s %s from %s - $%.2f%n",
//                    employee.getFirstName(),
//                    employee.getLastName(),
//                    employee.getDepartmentName(),
//                    employee.getSalary()));
//        }

//        System.out.println(sb);

        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e WHERE e.department.name IN (:department_names)", Employee.class)
                .setParameter("department_names", departmentNames)
                .getResultList();

        for (Employee employee : employees) {
            employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12)));
        }

        StringBuilder sb = new StringBuilder();
        for (Employee employee : employees) {
            sb.append(String.format("%s %s from %s - $%.2f%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDepartment().getName(),
                    employee.getSalary()));
        }

        System.out.println(sb);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
