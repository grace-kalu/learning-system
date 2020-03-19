package com.codeWithMerald.RoyalSealLearningSystem.services.student;

import com.codeWithMerald.RoyalSealLearningSystem.models.course.Course;
import com.codeWithMerald.RoyalSealLearningSystem.models.department.Department;
import com.codeWithMerald.RoyalSealLearningSystem.models.user.Student;
import com.codeWithMerald.RoyalSealLearningSystem.payload.enroll.StudentCourseEnrollment;
import com.codeWithMerald.RoyalSealLearningSystem.payload.StudentRequest;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.StudentRepository;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.course.CourseRepository;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.department.DepartmentRepository;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.student.StudentAssignmentRepository;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.test.QuizScoreRepository;
import com.codeWithMerald.RoyalSealLearningSystem.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService, EnrollStudent{
    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    private final QuizScoreRepository quizScoreRepository;

    private final StudentAssignmentRepository studentAssignmentRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, QuizScoreRepository quizScoreRepository, StudentAssignmentRepository studentAssignmentRepository, DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.quizScoreRepository = quizScoreRepository;
        this.studentAssignmentRepository = studentAssignmentRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>(studentRepository.findAll());
        System.out.println(students);
        return students;
    }

    @Override
    public Student getStudent(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);

    }

    @Override
    public Student updateStudent(Long studentId, StudentRequest newStudent) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if(student != null) {
            student.setFullName(newStudent.getFullName());
            student.setEmail(newStudent.getEmail());
            student.setProfileImageUrl(newStudent.getProfileImageUrl());
            student.setPhoneNumber(newStudent.getPhoneNumber());
            return studentRepository.save(student);
        }
        return null;
    }

    @Override
    public ApiResponse deleteStudent(Long studentId) {
        studentRepository.findById(studentId).ifPresent(studentRepository::delete);
        return new ApiResponse(Boolean.TRUE, "You successfully deleted profile of the student with this id: " + studentId);
    }

    @Override
    public ApiResponse enrollStudent(StudentCourseEnrollment studentCourseEnrollment) {
        Student student = studentRepository.findById(studentCourseEnrollment.getStudentId()).orElse(null);
        Course course = courseRepository.findById(studentCourseEnrollment.getCourseId()).orElse(null);
        if(course != null){
            course.getStudents().add(student);
            courseRepository.save(course);
        }

        return new ApiResponse(Boolean.TRUE, "You successfully enrolled for this course " + course);
    }

    @Override
    public ApiResponse unEnroll(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);
        if(course != null){
            course.getStudents().remove(student);
            courseRepository.save(course);
        }

        return new ApiResponse(Boolean.TRUE, "You are currently not enrolled for this course " + course);
    }

    @Override
    public Student mapStudentToCourse(Long departmentId, Long studentId) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);

        assert student != null;
        student.setDepartment(department);
        return studentRepository.save(student);
    }
}
