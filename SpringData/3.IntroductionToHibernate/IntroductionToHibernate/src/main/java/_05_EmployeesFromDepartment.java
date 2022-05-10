import data.EmployeeInfo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class _05_EmployeesFromDepartment {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        String departmentNameInput = "Research and Development";
//
//        Solution 1
//
//        List<Object[]> employees = entityManager.createQuery(
//                        "SELECT e.firstName, e.lastName, e.department.name, e.salary" +
//                                " FROM Employee e" +
//                                " WHERE e.department.name = :department_name" +
//                                " ORDER BY e.salary ASC," +
//                                " e.id ASC", Object[].class)
//                .setParameter("department_name", departmentNameInput)
//                .getResultList();
//
//        Object s = employees.get(0)[0];
//        for (Object[] employee : employees) {
//            String firstName = (String) employee[0];
//            String lastName = (String) employee[1];
//            String departmentName = (String) employee[2];
//            BigDecimal salary = (BigDecimal) employee[3];
//
//            System.out.printf("%s %s from %s - $%.2f%n",
//                    firstName,
//                    lastName,
//                    departmentName,
//                    salary);
//        }

//        Solution 2
        List<EmployeeInfo> employeesInfo = entityManager.createQuery(
                "SELECT NEW data.EmployeeInfo(e.firstName, e.lastName, e.department.name, e.salary)" +
                        " FROM Employee e" +
                        " WHERE e.department.name = :department_name" +
                        " ORDER BY e.salary ASC," +
                        " e.id ASC", EmployeeInfo.class)
                .setParameter("department_name", departmentNameInput)
                .getResultList();

        for (EmployeeInfo employee : employeesInfo) {
            System.out.printf("%s %s from %s - $%.2f%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDepartmentName(),
                    employee.getSalary());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }


}
