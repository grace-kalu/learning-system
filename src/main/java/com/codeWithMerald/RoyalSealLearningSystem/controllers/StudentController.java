package com.codeWithMerald.RoyalSealLearningSystem.controllers;

import com.codeWithMerald.RoyalSealLearningSystem.models.user.Student;
import com.codeWithMerald.RoyalSealLearningSystem.payload.StudentRequest;
import com.codeWithMerald.RoyalSealLearningSystem.responses.ApiResponse;
import com.codeWithMerald.RoyalSealLearningSystem.services.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> response = studentService.getAllStudents();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable(value = "studentId") Long studentId) {
        Student response = studentService.getStudent(studentId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "studentId") Long studentId,
                                           @Valid @RequestBody StudentRequest newStudent) {
        Student updatedStudent = studentService.updateStudent(studentId, newStudent);

        return new ResponseEntity<>(updatedStudent, HttpStatus.CREATED);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable(value = "studentId") Long studentId) {
        ApiResponse response = studentService.deleteStudent(studentId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
