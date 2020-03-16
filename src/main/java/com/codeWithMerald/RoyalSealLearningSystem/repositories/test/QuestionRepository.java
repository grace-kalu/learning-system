package com.codeWithMerald.RoyalSealLearningSystem.repositories.test;

import com.codeWithMerald.RoyalSealLearningSystem.models.test.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}

