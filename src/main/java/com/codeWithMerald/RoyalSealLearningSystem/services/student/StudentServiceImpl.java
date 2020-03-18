package com.codeWithMerald.RoyalSealLearningSystem.services.student;

import com.codeWithMerald.RoyalSealLearningSystem.exception.AppException;
import com.codeWithMerald.RoyalSealLearningSystem.models.user.Student;
import com.codeWithMerald.RoyalSealLearningSystem.payload.StudentRequest;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.StudentRepository;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.course.CourseRepository;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.student.StudentAssignmentRepository;
import com.codeWithMerald.RoyalSealLearningSystem.repositories.test.QuizScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    private final QuizScoreRepository quizScoreRepository;

    private final StudentAssignmentRepository studentAssignmentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, QuizScoreRepository quizScoreRepository, StudentAssignmentRepository studentAssignmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.quizScoreRepository = quizScoreRepository;
        this.studentAssignmentRepository = studentAssignmentRepository;
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
    public Student updateStudent(Long studentId, StudentRequest student) {
        return null;
    }

    @Override
    public void deleteStudent(Long studentId) {

    }
}
