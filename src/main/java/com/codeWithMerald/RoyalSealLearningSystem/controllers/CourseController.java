package com.codeWithMerald.RoyalSealLearningSystem.controllers;

import com.codeWithMerald.RoyalSealLearningSystem.models.course.Course;
import com.codeWithMerald.RoyalSealLearningSystem.models.test.Quiz;
import com.codeWithMerald.RoyalSealLearningSystem.models.user.Student;
import com.codeWithMerald.RoyalSealLearningSystem.payload.CourseDTO;
import com.codeWithMerald.RoyalSealLearningSystem.responses.ApiResponse;
import com.codeWithMerald.RoyalSealLearningSystem.services.course.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/courses")
public class CourseController {
    private final CourseServiceImpl courseService;

    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @GetMapping()
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> response = courseService.getAllCourses();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<List<CourseDTO>> getAllCoursesInfo() {
        List<CourseDTO> response = courseService.getAllCoursesInformation();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{courseId}")
    @ResponseBody
    public  ResponseEntity<Course> getCourse(@PathVariable("courseId") Long courseId){
        Course response = courseService.getCourse(courseId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/course/add")
    @ResponseBody()
    public ResponseEntity<Course> createCourse (@Valid @RequestBody Course course){

        Course response = courseService.createCourse(course);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/course/{courseId}")
    @ResponseBody()
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable(value = "courseId") Long courseId) {
        ApiResponse response = courseService.deleteCourse(courseId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{courseId}/quizzes")
    @ResponseBody()
    public ResponseEntity<List<Quiz>> getCourseQuizzes(@PathVariable("courseId") Long courseId){
        List<Quiz> response = courseService.getQuizForCourse(courseId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
