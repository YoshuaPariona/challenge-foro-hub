package com.yoshua.api_forum.controller;

import com.yoshua.api_forum.domain.topic.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicService topicService;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailTopic> registerTopic(@RequestBody @Valid DataRegisterTopic dataTopic) {

        var detailTopic = topicService.registerTopic(dataTopic);
        return ResponseEntity.ok(detailTopic);
    }

    @GetMapping
    public Page<DataListTopic> listTopic(@PageableDefault(size=5, sort = {"creationDate"}) Pageable pageable) {
        return topicRepository.findAllByStatusTrue(pageable).map(DataListTopic::new);
    }
}
