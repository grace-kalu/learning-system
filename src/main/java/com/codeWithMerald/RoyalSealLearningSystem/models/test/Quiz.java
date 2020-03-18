package com.codeWithMerald.RoyalSealLearningSystem.models.test;

import com.codeWithMerald.RoyalSealLearningSystem.models.course.Course;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "quiz")
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    private String name;
    private Date date;
    private int duration;

    @ColumnDefault("false")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questions;

    public Quiz() {
        this.questions = new ArrayList<>();
    }

    public Quiz(String name, Course course) {
        this.questions = new ArrayList<>();
        this.name = name;
        this.course = course;
    }

}
