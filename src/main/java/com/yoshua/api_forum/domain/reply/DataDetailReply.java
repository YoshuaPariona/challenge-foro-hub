package com.yoshua.api_forum.domain.reply;

import java.time.LocalDate;

public record DataDetailReply(

        Long replyId,
        LocalDate creationDate,
        Long topicId,
        String topicTitle,
        Long userId,
        String userEmail,
        String message,
        Boolean solution
) {
    public DataDetailReply(Reply reply) {
        this(
                reply.getReplyId(),
                reply.getCreationDate(),
                reply.getTopic().getTopicId(),
                reply.getTopic().getTitle(),
                reply.getUser().getUserId(),
                reply.getUser().getEmail(),
                reply.getMessage(),
                reply.getSolution()
        );
    }
}
