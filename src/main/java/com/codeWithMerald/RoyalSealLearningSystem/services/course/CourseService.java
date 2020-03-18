
package com.codeWithMerald.RoyalSealLearningSystem.services.course;

import com.codeWithMerald.RoyalSealLearningSystem.models.course.Course;
import com.codeWithMerald.RoyalSealLearningSystem.models.department.Department;
import com.codeWithMerald.RoyalSealLearningSystem.models.teacher.Teacher;
import com.codeWithMerald.RoyalSealLearningSystem.models.test.Quiz;
import com.codeWithMerald.RoyalSealLearningSystem.models.user.Student;
import com.codeWithMerald.RoyalSealLearningSystem.payload.CourseDTO;
import com.codeWithMerald.RoyalSealLearningSystem.responses.ApiResponse;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    List<CourseDTO> getAllCoursesInformation();
    Course getCourse(Long courseId);
    Course createCourse(Course course);
    ApiResponse deleteCourse(Long courseId);
}

