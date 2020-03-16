package com.codeWithMerald.RoyalSealLearningSystem.models.teacher;

import com.codeWithMerald.RoyalSealLearningSystem.models.course.Course;
import com.codeWithMerald.RoyalSealLearningSystem.models.department.Department;
import com.codeWithMerald.RoyalSealLearningSystem.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teacher")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long teacherId;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(mappedBy = "teachers")
    private Set<Course> courses;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
