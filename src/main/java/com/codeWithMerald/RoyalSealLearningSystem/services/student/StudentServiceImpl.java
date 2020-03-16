package com.codeWithMerald.RoyalSealLearningSystem.services.student;

import com.codeWithMerald.RoyalSealLearningSystem.exception.AppException;
import com.codeWithMerald.RoyalSealLearningSystem.models.student.Student;
import com.codeWithMerald.RoyalSealLearningSystem.models.user.User;
import com.codeWithMerald.RoyalSealLearningSystem.payload.StudentRequest;
import com.codeWithMerald.RoyalSealLearningSystem.payload.auth.SignUpRequest;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.UserRepository;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.course.CourseRepository;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.student.StudentAssignmentRepository;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.student.StudentRepository;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.test.TestScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    private final TestScoreRepository testScoreRepository;

    private final StudentAssignmentRepository studentAssignmentRepository;

    private final UserRepository userRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, TestScoreRepository testScoreRepository, StudentAssignmentRepository studentAssignmentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.testScoreRepository = testScoreRepository;
        this.studentAssignmentRepository = studentAssignmentRepository;
        this.userRepository = userRepository;
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
    public Student createStudent(SignUpRequest newStudent) {
        Student student = new User();

        return null;
    }

    @Override
    public Student updateStudent(Long studentId, StudentRequest student) {
        return null;
    }

    @Override
    public void deleteStudent(Long studentId) {

    }
}
