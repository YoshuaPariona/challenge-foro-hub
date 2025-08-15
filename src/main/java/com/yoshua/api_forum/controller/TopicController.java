package com.yoshua.api_forum.controller;

import com.yoshua.api_forum.domain.topic.*;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicService topicService;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailTopic> registerTopic(@RequestBody @Valid DataRegisterTopic dataTopic,
                                                         UriComponentsBuilder uriComponentsBuilder) {
        var detailTopic = topicService.registerTopic(dataTopic);
        URI uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(detailTopic.topicId()).toUri();

        return ResponseEntity.created(uri).body(detailTopic);
    }

    @GetMapping
    public ResponseEntity<Page<DataListTopic>> listTopic(@PageableDefault(size=5, sort = {"creationDate"}) Pageable pageable) {
        return ResponseEntity.ok(topicRepository.findAll(pageable).map(DataListTopic::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailTopic> returnTopic(@PathVariable Long id) {
        if(!topicRepository.existsById(id)) {
            throw new ValidationException("No existe un tópico con el id proporcionado.");
        }
        var topic = topicRepository.getReferenceById(id);

        return ResponseEntity.ok(new DataDetailTopic(topic));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataDetailTopic> updateTopic(@RequestBody @Valid DataUpdateTopic dataTopic) {
        if(!topicRepository.existsById(dataTopic.topicId())) {
            throw new ValidationException("No existe un tópico con el id proporcionado.");
        }
        var topic = topicRepository.getReferenceById(dataTopic.topicId());
        topic.updateTopic(dataTopic);
        return ResponseEntity.ok(new DataDetailTopic(topic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        if(!topicRepository.existsById(id)) {
            throw new ValidationException("No existe un tópico con el id proporcionado.");
        }
        topicRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
