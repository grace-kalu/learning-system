package com.codeWithMerald.RoyalSealLearningSystem.repositories.department;

import com.codeWithMerald.RoyalSealLearningSystem.models.department.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
