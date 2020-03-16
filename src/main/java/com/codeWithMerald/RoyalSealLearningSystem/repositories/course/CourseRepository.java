package com.codeWithMerald.RoyalSealLearningSystem.repositories.course;

import com.codeWithMerald.RoyalSealLearningSystem.models.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
