package com.codeWithMerald.RoyalSealLearningSystem.models.student;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentAssignmentPK implements Serializable {

    @Column(name = "student_id")
    private Long student_id;

    @Column(name = "assignment_id")
    private Long assignment_id;

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Long getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(Long assignment_id) {
        this.assignment_id = assignment_id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        StudentAssignmentPK assign = (StudentAssignmentPK) object;

        if (!Objects.equals(student_id, assign.student_id)) return false;
        return Objects.equals(assignment_id, assign.assignment_id);
    }

    @Override
    public int hashCode() {
        int result = student_id != null ? student_id.hashCode() : 0;
        result = 30 * result + (assignment_id != null ? assignment_id.hashCode() : 0);
        return result;
    }
}
