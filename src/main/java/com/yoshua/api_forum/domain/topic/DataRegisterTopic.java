package com.yoshua.api_forum.domain.topic;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterTopic(
        @NotBlank String title,
        @NotBlank String message,
        @JsonAlias("user_id") @NotNull Long userId,
        @JsonAlias("course_id") @NotNull Long courseId
) {
}
