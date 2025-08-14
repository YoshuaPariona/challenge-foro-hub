package com.yoshua.api_forum.controller;

import com.yoshua.api_forum.domain.reply.DataRegisterReply;
import com.yoshua.api_forum.domain.reply.Reply;
import com.yoshua.api_forum.domain.reply.ReplyRepository;
import com.yoshua.api_forum.domain.topic.TopicRepository;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/replies")
public class ReplyController {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Transactional
    @PostMapping
    public void registerReply(@RequestBody @Valid DataRegisterReply dataReply) {
        if(!topicRepository.existsById(dataReply.topicId())) {
            throw new ValidationException("No existe un tópico con el id proporcionado.");
        }
        var topic = topicRepository.getReferenceById(dataReply.topicId());
        replyRepository.save(new Reply(null, LocalDate.now() , topic, dataReply.message(), false, dataReply.userId()));
    }
}
