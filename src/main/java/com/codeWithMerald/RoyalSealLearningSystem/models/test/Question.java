package com.codeWithMerald.RoyalSealLearningSystem.models.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private String question;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @JsonIgnore
    private Test test;

    @ElementCollection
    private List<String> answers;

    private String correctAnswer;

    public Question() {
        this.answers = new ArrayList<>();
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
