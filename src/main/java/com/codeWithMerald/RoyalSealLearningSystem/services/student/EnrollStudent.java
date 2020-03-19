package com.codeWithMerald.RoyalSealLearningSystem.services.student;

import com.codeWithMerald.RoyalSealLearningSystem.models.user.Student;
import com.codeWithMerald.RoyalSealLearningSystem.payload.enroll.StudentCourseEnrollment;
import com.codeWithMerald.RoyalSealLearningSystem.responses.ApiResponse;

public interface EnrollStudent {
    ApiResponse enrollStudent(StudentCourseEnrollment studentCourseEnrollment);
    ApiResponse unEnroll(Long studentId, Long courseId);
    Student mapStudentToCourse(Long departmentId, Long studentId);
}
