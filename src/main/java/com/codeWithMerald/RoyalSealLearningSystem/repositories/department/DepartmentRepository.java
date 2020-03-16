package com.codeWithMerald.RoyalSealLearningSystem.repositories.department;

import com.codeWithMerald.RoyalSealLearningSystem.models.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
