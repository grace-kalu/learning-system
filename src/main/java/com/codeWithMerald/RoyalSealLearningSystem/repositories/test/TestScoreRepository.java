package com.codeWithMerald.RoyalSealLearningSystem.repositories.test;

import com.codeWithMerald.RoyalSealLearningSystem.models.student.Student;
import com.codeWithMerald.RoyalSealLearningSystem.models.test.Test;
import com.codeWithMerald.RoyalSealLearningSystem.models.test.TestScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestScoreRepository extends JpaRepository<TestScore, Long> {
    List<TestScore> findByStudent(Student student);
    List<TestScore> findByTest(Test test);
}
