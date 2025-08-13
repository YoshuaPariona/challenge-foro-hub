package com.yoshua.api_forum.controller;

import com.yoshua.api_forum.domain.topic.DataRegisterTopic;
import com.yoshua.api_forum.domain.topic.Topic;
import com.yoshua.api_forum.domain.topic.TopicRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    public void registerTopic(@RequestBody @Valid DataRegisterTopic dataTopic) {
        topicRepository.save(new Topic(dataTopic));
    }
}
