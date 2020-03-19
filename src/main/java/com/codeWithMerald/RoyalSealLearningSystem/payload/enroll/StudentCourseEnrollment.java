package com.codeWithMerald.RoyalSealLearningSystem.payload.enroll;

public class StudentCourseEnrollment {
    private Long courseId;
    private Long studentId;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
