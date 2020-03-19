package com.codeWithMerald.RoyalSealLearningSystem.payload.enroll;

public class DepartmentEnrollment {
    private Long departmentId;
    private Long courseId;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
