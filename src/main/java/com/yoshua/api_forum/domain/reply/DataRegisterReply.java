package com.yoshua.api_forum.domain.reply;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterReply(
        @NotBlank
        String message,

        @NotNull
        @JsonAlias("user_id")
        Long userId,

        @NotNull
        @JsonAlias("topic_id")
        Long topicId
) {
}
