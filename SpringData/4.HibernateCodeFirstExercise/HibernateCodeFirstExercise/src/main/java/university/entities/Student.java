package university.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Student extends Person{

    @Column(name = "average_grade")
    private Double averageGrade;

    @Column
    private int attendance;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;

    protected Student() {
    }

    public Student(String firstName, String lastName, String phoneNumber, Double averageGrade, int attendance) {
        super(firstName, lastName, phoneNumber);
        this.averageGrade = averageGrade;
        this.attendance = attendance;
        courses = new LinkedHashSet<>();
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    private void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public int getAttendance() {
        return attendance;
    }

    private void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }
}
