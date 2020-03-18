package com.codeWithMerald.RoyalSealLearningSystem.repositories.course;

import com.codeWithMerald.RoyalSealLearningSystem.models.course.Course;
import com.codeWithMerald.RoyalSealLearningSystem.models.department.Department;
import com.codeWithMerald.RoyalSealLearningSystem.models.teacher.Teacher;
import com.codeWithMerald.RoyalSealLearningSystem.models.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = "SELECT course.students FROM Course course WHERE course.courseId = ?1")
    List<Student> getStudentForCourse(Long courseId);
    @Query(value = "SELECT course.department FROM Course course WHERE course.courseId = ?1")
    List<Department> getDepartmentsForCourse(Long courseId);
    @Query(value = "SELECT course.teachers FROM Course course WHERE course.courseId = ?1")
    List<Teacher> getTeachersForCourse(Long courseId);
}
