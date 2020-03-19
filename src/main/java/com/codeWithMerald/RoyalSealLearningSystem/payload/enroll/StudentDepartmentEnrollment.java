package com.codeWithMerald.RoyalSealLearningSystem.payload.enroll;

public class StudentDepartmentEnrollment {
    private Long departmentId;
    private Long studentId;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
