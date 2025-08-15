package com.yoshua.api_forum.domain.topic;

import java.time.LocalDate;

public record DataDetailTopic(

        Long topicId,
        LocalDate creationDate,
        Long userId,
        Long courseId,
        String userEmail,
        String courseName,
        String title,
        String message,
        String status
) {
    public DataDetailTopic(Topic topic) {
        this(
                topic.getTopicId(),
                topic.getCreationDate(),
                topic.getUser().getUserId(),
                topic.getCourse().getCourseId(),
                topic.getUser().getEmail(),
                topic.getCourse().getName(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getStatus()
        );
    }
}
