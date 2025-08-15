package com.yoshua.api_forum.domain.topic;

import java.time.LocalDate;

public record DataListTopic(
        Long topicId,
        String title,
        String message,
        String status,
        LocalDate creationDate
) {
    public DataListTopic(Topic topic) {
        this(
                topic.getTopicId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getStatus(),
                topic.getCreationDate()
        );
    }
}
