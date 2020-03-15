package com.codeWithMerald.RoyalSealLearningSystem.repositories.student;

import com.codeWithMerald.RoyalSealLearningSystem.models.student.StudentAssignment;
import com.codeWithMerald.RoyalSealLearningSystem.models.student.StudentAssignmentPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAssignmentRepository extends JpaRepository<StudentAssignment, StudentAssignmentPK> {
}
