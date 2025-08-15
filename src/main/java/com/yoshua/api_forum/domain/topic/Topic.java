package com.yoshua.api_forum.domain.topic;

import com.yoshua.api_forum.domain.course.Course;
import com.yoshua.api_forum.domain.user.User;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String title;
    private String message;
    private String status;

    public void updateTopic(DataUpdateTopic dataUpdateTopic) {
        if(dataUpdateTopic.title() != null) {
            this.title = dataUpdateTopic.title();
        }
        if(dataUpdateTopic.message() != null) {
            this.message = dataUpdateTopic.message();
        }
        if(dataUpdateTopic.status() != null) {
            this.status = dataUpdateTopic.status();
        }
    }
}
