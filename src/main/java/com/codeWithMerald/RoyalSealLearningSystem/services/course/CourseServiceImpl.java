package com.codeWithMerald.RoyalSealLearningSystem.services.course;

import com.codeWithMerald.RoyalSealLearningSystem.models.course.Course;
import com.codeWithMerald.RoyalSealLearningSystem.models.department.Department;
import com.codeWithMerald.RoyalSealLearningSystem.models.teacher.Teacher;
import com.codeWithMerald.RoyalSealLearningSystem.models.test.Quiz;
import com.codeWithMerald.RoyalSealLearningSystem.models.user.Student;
import com.codeWithMerald.RoyalSealLearningSystem.payload.CourseDTO;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.course.CourseRepository;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.department.DepartmentRepository;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.test.QuizRepository;
import com.codeWithMerald.RoyalSealLearningSystem.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService, PeopleService {
    private final CourseRepository courseRepository;
    private final QuizRepository quizRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, QuizRepository quizRepository, DepartmentRepository departmentRepository) {
        this.courseRepository = courseRepository;
        this.quizRepository = quizRepository;
        this.departmentRepository = departmentRepository;
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

    @Override
    public List<Student> getStudentForCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        assert course != null;
        return courseRepository.getStudentForCourse(courseId);
    }
    @Override
    public List<Teacher> getTeachersForCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        assert course != null;
        return courseRepository.getTeachersForCourse(courseId);
    }

    @Override
    public List<Department> getDepartmentsForCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        assert course != null;
        return courseRepository.getDepartmentsForCourse(courseId);
    }

    @Override
    public Course mapCoursesToDepartment(Long departmentId, Long courseId){

        Department department = departmentRepository.findById(departmentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);
        if(course != null && department !=null){
            course.setDepartment(department);
            return courseRepository.save(course);
        }
      return null;
    }
}
