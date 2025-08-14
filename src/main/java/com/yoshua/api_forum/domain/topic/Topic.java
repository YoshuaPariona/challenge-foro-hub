package com.yoshua.api_forum.domain.topic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicId;

    @CreationTimestamp
    private LocalDate creationDate;

    private String title;
    private String message;
    private Boolean status;
        /*
    @ManyToOne
    @JoinColumn(name = "user_id")
    @ManyToOne
    @JoinColumn(name = "course_id")
    */
    private Long userId;
    private Long courseId;

    public Topic(DataRegisterTopic dataTopic) {
        this.topicId = null;
        this.title = dataTopic.title();
        this.message = dataTopic.message();
        this.status = false;
        this.userId = dataTopic.userId();
        this.courseId = dataTopic.courseId();
    }
}
