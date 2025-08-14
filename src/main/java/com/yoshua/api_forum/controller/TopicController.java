package com.yoshua.api_forum.controller;

import com.yoshua.api_forum.domain.topic.DataListTopic;
import com.yoshua.api_forum.domain.topic.DataRegisterTopic;
import com.yoshua.api_forum.domain.topic.Topic;
import com.yoshua.api_forum.domain.topic.TopicRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Transactional
    @PostMapping
    public void registerTopic(@RequestBody @Valid DataRegisterTopic dataTopic) {
        topicRepository.save(new Topic(dataTopic));
    }

    @GetMapping
    public Page<DataListTopic> listTopic(@PageableDefault(size=5, sort = {"title"}) Pageable pageable) {
        return topicRepository.findAllByStatusTrue(pageable).map(DataListTopic::new);
    }
}
