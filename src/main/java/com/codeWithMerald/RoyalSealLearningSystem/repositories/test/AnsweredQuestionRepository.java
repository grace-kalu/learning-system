package com.codeWithMerald.RoyalSealLearningSystem.repositories.test;

import com.codeWithMerald.RoyalSealLearningSystem.models.test.AnsweredQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnsweredQuestionRepository extends JpaRepository<AnsweredQuestion, Long> {
}
