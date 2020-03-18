package com.codeWithMerald.RoyalSealLearningSystem.repositories.test;


import com.codeWithMerald.RoyalSealLearningSystem.models.test.Quiz;
import com.codeWithMerald.RoyalSealLearningSystem.models.test.QuizScore;
import com.codeWithMerald.RoyalSealLearningSystem.models.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizScoreRepository extends JpaRepository<QuizScore, Long> {
    List<QuizScore> findByStudent(Student student);
    List<QuizScore> findByQuiz(Quiz quiz);
}
