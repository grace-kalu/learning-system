package com.codeWithMerald.RoyalSealLearningSystem.services.course;

import com.codeWithMerald.RoyalSealLearningSystem.models.department.Department;
import com.codeWithMerald.RoyalSealLearningSystem.models.teacher.Teacher;
import com.codeWithMerald.RoyalSealLearningSystem.models.test.Quiz;
import com.codeWithMerald.RoyalSealLearningSystem.models.user.Student;

import java.util.List;

public interface PeopleService {
    List<Quiz> getQuizForCourse(Long courseId);
    List<Student> getStudentForCourse(Long courseId);
    List<Department> getDepartmentsForCourse(Long courseId);
    List<Teacher> getTeachersForCourse(Long courseId);
}
