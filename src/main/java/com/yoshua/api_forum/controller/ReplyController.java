package com.yoshua.api_forum.controller;

import com.yoshua.api_forum.domain.reply.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/replies")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailReply> registerReply(@RequestBody @Valid DataRegisterReply dataReply) {
        var detailReply = replyService.registerReply(dataReply);
        return ResponseEntity.ok(detailReply);
    }
}
