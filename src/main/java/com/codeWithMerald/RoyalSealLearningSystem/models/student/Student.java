package com.codeWithMerald.RoyalSealLearningSystem.models.student;

import com.codeWithMerald.RoyalSealLearningSystem.models.course.Course;
import com.codeWithMerald.RoyalSealLearningSystem.models.department.Department;
import com.codeWithMerald.RoyalSealLearningSystem.models.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "studentId")
public class Student extends User {
    @NonNull

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentId;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;

    @OneToMany(mappedBy = "assignment")
    private Set<StudentAssignment> studentAssignment = new HashSet<StudentAssignment>();

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<StudentAssignment> getStudentAssignment() {
        return studentAssignment;
    }

    public void setStudentAssignment(Set<StudentAssignment> studentAssignment) {
        this.studentAssignment = studentAssignment;
    }

    public void addStudentAssignment(StudentAssignment studentAssignment){
        this.studentAssignment.add(studentAssignment);
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId + '}';
    }
}
