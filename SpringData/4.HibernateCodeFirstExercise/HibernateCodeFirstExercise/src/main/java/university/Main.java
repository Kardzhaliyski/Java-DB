package university;

import university.entities.Course;
import university.entities.Student;
import university.entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Course course = new Course(
                "Java Basic",
                "First programming course with Java",
                LocalDate.now(),
                LocalDate.of(2022, 8, 28));
        Student student = new Student(
                "Gosho",
                "Goshev",
                "0888121314",
                5.0,
                100);
        Teacher teacher = new Teacher(
                "Pesho",
                "Peshev",
                "0876112233",
                "Pesho@peshev.com",
                BigDecimal.valueOf(5.79));

        course.setTeacher(teacher);
        course.addStudent(student);
        teacher.addCourse(course);
        student.addCourse(course);

        entityManager.persist(teacher);
        entityManager.persist(course);
        entityManager.persist(student);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
