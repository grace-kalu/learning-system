package com.codeWithMerald.RoyalSealLearningSystem.services.student;

import com.codeWithMerald.RoyalSealLearningSystem.payload.AssignmentSubmission;
import com.codeWithMerald.RoyalSealLearningSystem.payload.CourseDTO;
import com.codeWithMerald.RoyalSealLearningSystem.payload.StudentAssignmentDTO;
import com.codeWithMerald.RoyalSealLearningSystem.payload.TestMarks;

import java.util.List;

public interface StudentResult {
    List<TestMarks> getStudentTestMarks(Long studentId);
    List<StudentAssignmentDTO> getAllAssignmentsForStudent(String studentId);
    List<AssignmentSubmission> getAllAssignments(Long assignmentId);
    List<CourseDTO> getAllCoursesForStudent(String studentId);
}
