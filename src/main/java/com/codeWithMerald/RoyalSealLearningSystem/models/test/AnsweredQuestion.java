package com.codeWithMerald.RoyalSealLearningSystem.models.test;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "answered_questions")
@Data
@NoArgsConstructor
public class AnsweredQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ansQuestionId;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;

    private String selectedAnswer;

    public AnsweredQuestion(Question question, String selectedAnswer) {
        this.question = question;
        this.selectedAnswer = selectedAnswer;
    }

    public Long getAnsQuestionId() {
        return ansQuestionId;
    }

    public void setAnsQuestionId(Long ansQuestionId) {
        this.ansQuestionId = ansQuestionId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
