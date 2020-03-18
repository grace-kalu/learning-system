package com.codeWithMerald.RoyalSealLearningSystem.models.student;

import com.codeWithMerald.RoyalSealLearningSystem.models.course.Assignment;
import com.codeWithMerald.RoyalSealLearningSystem.models.user.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "student_assignment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAssignment {

    @JsonIgnore
    @EmbeddedId
    private StudentAssignmentPK id = new StudentAssignmentPK();

    @ManyToOne
    @MapsId("student_id")
    @JoinColumn(name = "student_id", referencedColumnName = "studentId")
    private Student student;

    @ManyToOne
    @MapsId("assignment_id")
    @JoinColumn(name = "assignment_id", referencedColumnName = "assignmentId")
    private Assignment assignment;

    @Column(name = "grade")
    private long marks;

    @Column(name = "file")
    private String file;

    @Column(name = "feedback")
    private String feedback;

    public StudentAssignmentPK getId() {
        return id;
    }

    public void setId(StudentAssignmentPK id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public long getMarks() {
        return marks;
    }

    public void setMarks(long marks) {
        this.marks = marks;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
