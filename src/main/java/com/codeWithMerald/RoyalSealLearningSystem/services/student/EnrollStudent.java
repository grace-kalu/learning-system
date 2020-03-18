package com.codeWithMerald.RoyalSealLearningSystem.services.student;

import com.codeWithMerald.RoyalSealLearningSystem.payload.Enrollment;
import com.codeWithMerald.RoyalSealLearningSystem.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface EnrollStudent {
    ApiResponse enrollStudent(Enrollment enrollment);
    ApiResponse unEnroll(Long studentId, Long courseId);
}
