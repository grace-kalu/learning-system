package com.codeWithMerald.RoyalSealLearningSystem.services.student;

import com.codeWithMerald.RoyalSealLearningSystem.payload.Enrollment;
import org.springframework.http.ResponseEntity;

public interface EnrollStudent {
    ResponseEntity<String> enrollStudent(Enrollment enrollment);
    ResponseEntity<String> unEnroll(String studentId, String courseId);
}
