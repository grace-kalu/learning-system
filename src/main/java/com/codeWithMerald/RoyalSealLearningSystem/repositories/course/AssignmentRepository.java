package com.codeWithMerald.RoyalSealLearningSystem.repositories.course;

import com.codeWithMerald.RoyalSealLearningSystem.models.course.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
