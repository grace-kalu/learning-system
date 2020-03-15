package com.codeWithMerald.RoyalSealLearningSystem.repositories;

import com.codeWithMerald.RoyalSealLearningSystem.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    Optional<User> findById(Long id);
    boolean existsByEmailOrUsername(String email, String username);
    boolean existsByEmail(String email);
    User findByEmailVerificationToken(String token);
}
