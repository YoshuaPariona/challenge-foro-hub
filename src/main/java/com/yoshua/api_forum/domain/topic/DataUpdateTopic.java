package com.yoshua.api_forum.domain.topic;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record DataUpdateTopic(
        @NotNull
        @JsonAlias("topic_id")
        Long topicId,

        String title,
        String message,
        String status

) {
    public DataUpdateTopic(Topic topic) {
        this(
                topic.getTopicId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getStatus()
        );
    }
}
