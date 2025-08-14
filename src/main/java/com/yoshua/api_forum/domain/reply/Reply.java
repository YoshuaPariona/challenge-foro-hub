package com.yoshua.api_forum.domain.reply;

import com.yoshua.api_forum.domain.topic.Topic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Table(name = "replies")
@Entity(name = "Reply")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @CreationTimestamp
    private LocalDate creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    private String message;
    private Boolean solution;
    private Long userId;

}
