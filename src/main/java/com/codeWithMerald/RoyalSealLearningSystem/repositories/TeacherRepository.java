package com.codeWithMerald.RoyalSealLearningSystem.repositories;

import com.codeWithMerald.RoyalSealLearningSystem.models.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByEmail(String email);
}
