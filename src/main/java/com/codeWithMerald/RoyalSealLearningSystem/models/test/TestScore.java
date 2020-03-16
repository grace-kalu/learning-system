package com.codeWithMerald.RoyalSealLearningSystem.models.test;

import com.codeWithMerald.RoyalSealLearningSystem.models.student.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "testscore")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testScoreId;

    private Long marks;

    @OneToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany
    @JoinColumn(name = "correctQuestions")
    private List<AnsweredQuestion> answeredQuestions;

    public Long getTestScoreId() {
        return testScoreId;
    }

    public void setTestScoreId(Long testScoreId) {
        this.testScoreId = testScoreId;
    }

    public Long getMarks() {
        return marks;
    }

    public void setMarks(Long marks) {
        this.marks = marks;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<AnsweredQuestion> getAnsweredQuestions() {
        return answeredQuestions;
    }

    public void setAnsweredQuestions(List<AnsweredQuestion> answeredQuestions) {
        this.answeredQuestions = answeredQuestions;
    }
}
