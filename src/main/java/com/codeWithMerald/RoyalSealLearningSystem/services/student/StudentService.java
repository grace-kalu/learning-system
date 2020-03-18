package com.codeWithMerald.RoyalSealLearningSystem.services.student;

import com.codeWithMerald.RoyalSealLearningSystem.models.user.Student;
import com.codeWithMerald.RoyalSealLearningSystem.payload.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudent(Long studentId);
    Student updateStudent(Long studentId, StudentRequest student);
    void deleteStudent(Long studentId);
}
