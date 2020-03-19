package com.codeWithMerald.RoyalSealLearningSystem.controllers;

import com.codeWithMerald.RoyalSealLearningSystem.models.course.Course;
import com.codeWithMerald.RoyalSealLearningSystem.models.user.Student;
import com.codeWithMerald.RoyalSealLearningSystem.payload.enroll.DepartmentEnrollment;
import com.codeWithMerald.RoyalSealLearningSystem.payload.enroll.StudentCourseEnrollment;
import com.codeWithMerald.RoyalSealLearningSystem.payload.StudentRequest;
import com.codeWithMerald.RoyalSealLearningSystem.payload.enroll.StudentDepartmentEnrollment;
import com.codeWithMerald.RoyalSealLearningSystem.responses.ApiResponse;
import com.codeWithMerald.RoyalSealLearningSystem.services.student.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/students")
public class StudentController {
    private final StudentServiceImpl studentService;

    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> response = studentService.getAllStudents();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{studentId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Student> getStudent(@PathVariable(value = "studentId") Long studentId) {
        Student response = studentService.getStudent(studentId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{studentId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "studentId") Long studentId,
                                           @Valid @RequestBody StudentRequest newStudent) {
        Student updatedStudent = studentService.updateStudent(studentId, newStudent);

        return new ResponseEntity<>(updatedStudent, HttpStatus.CREATED);
    }

    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable(value = "studentId") Long studentId) {
        ApiResponse response = studentService.deleteStudent(studentId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/enroll")
    @ResponseBody
    public ResponseEntity<ApiResponse> enrollStudent(@RequestBody StudentCourseEnrollment studentCourseEnrollment){
        ApiResponse apiResponse =  studentService.enrollStudent(studentCourseEnrollment);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{studentId}/unenroll/{courseId}")
    @ResponseBody
    public ResponseEntity<ApiResponse> unEnrollStudent(@PathVariable(value = "studentId") Long studentId,
                                                       @PathVariable(value = "courseId") Long courseId){
        ApiResponse apiResponse =  studentService.unEnroll(studentId, courseId);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PatchMapping("/map")
    @ResponseBody()
    public ResponseEntity<Student>mapCoursesToDepartment(@Valid @RequestBody StudentDepartmentEnrollment enrollment){
        Student response = studentService.mapStudentToCourse(enrollment.getDepartmentId(), enrollment.getStudentId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
