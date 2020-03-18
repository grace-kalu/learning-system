package com.codeWithMerald.RoyalSealLearningSystem.services.course;

import com.codeWithMerald.RoyalSealLearningSystem.models.course.Course;
import com.codeWithMerald.RoyalSealLearningSystem.models.test.Quiz;
import com.codeWithMerald.RoyalSealLearningSystem.payload.CourseDTO;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.course.CourseRepository;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.test.QuizRepository;
import com.codeWithMerald.RoyalSealLearningSystem.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, QuizRepository quizRepository) {
        this.courseRepository = courseRepository;
        this.quizRepository = quizRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return new ArrayList<>(courseRepository.findAll());
    }

    @Override
    public List<CourseDTO> getAllCoursesInformation() {
        List<CourseDTO> coursesDTO = new ArrayList<>();
        courseRepository.findAll().forEach(course -> {
            CourseDTO courseInfo = new CourseDTO();
            courseInfo.setCourseId(course.getCourseId());
            courseInfo.setTitle(course.getTitle());
            courseInfo.setDescription(course.getDescription());
            courseInfo.setCode(course.getCode());
            coursesDTO.add(courseInfo);
        });
        return coursesDTO;
    }

    @Override
    public Course getCourse(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public ApiResponse deleteCourse(Long courseId) {
        courseRepository.findById(courseId).ifPresent(courseRepository::delete);
        return new ApiResponse(Boolean.TRUE, "You successfully deleted the course with this id: " + courseId);
    }

    @Override
    public List<Quiz> getQuizForCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        return quizRepository.findByCourse(course);
    }
}