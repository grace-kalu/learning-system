package com.codeWithMerald.RoyalSealLearningSystem.repositories;

import com.codeWithMerald.RoyalSealLearningSystem.models.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByEmail(String email);
}
