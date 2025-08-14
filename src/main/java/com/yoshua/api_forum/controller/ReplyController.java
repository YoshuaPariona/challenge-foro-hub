package com.yoshua.api_forum.controller;

import com.yoshua.api_forum.domain.reply.DataRegisterReply;
import com.yoshua.api_forum.domain.reply.Reply;
import com.yoshua.api_forum.domain.reply.ReplyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/replies")
public class ReplyController {

    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    @PostMapping
    public void registerReply(@RequestBody @Valid DataRegisterReply dataReply) {
        replyRepository.save(new Reply(dataReply));
    }
}
