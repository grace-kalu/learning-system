package com.codeWithMerald.RoyalSealLearningSystem.models.test;

import com.codeWithMerald.RoyalSealLearningSystem.models.user.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "quizscore")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testScoreId;

    private Long marks;

    @OneToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany
    @JoinColumn(name = "correctQuestions")
    private List<AnsweredQuestion> answeredQuestions;

    public Long getQuizScoreId() {
        return testScoreId;
    }

    public void setQuizScoreId(Long testScoreId) {
        this.testScoreId = testScoreId;
    }

    public Long getMarks() {
        return marks;
    }

    public void setMarks(Long marks) {
        this.marks = marks;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
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
