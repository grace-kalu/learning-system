package com.codeWithMerald.RoyalSealLearningSystem.repositories;

import com.codeWithMerald.RoyalSealLearningSystem.models.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
    Optional<Student> findById(Long id);
//    boolean existsByEmail(String email, String username);
    boolean existsByEmail(String email);
    Student findByEmailVerificationToken(String token);
}
