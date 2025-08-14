package com.yoshua.api_forum.domain.reply;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
@Table(name = "replies")
@Entity(name = "Reply")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @CreationTimestamp
    private LocalDate creationDate;

    private String message;
    private Boolean solution;

    private Long userId;
    private Long topicId;

    public Reply(DataRegisterReply dataReply) {
        this.replyId = null;
        this.message = dataReply.message();
        this.solution = false;
        this.userId = dataReply.userId();
        this.topicId = dataReply.topicId();

    }
}
