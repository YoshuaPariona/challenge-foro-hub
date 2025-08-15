package com.yoshua.api_forum.domain.topic;

import com.yoshua.api_forum.domain.course.CourseRepository;
import com.yoshua.api_forum.domain.user.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public DataDetailTopic registerTopic(DataRegisterTopic dataTopic) {
        if(!courseRepository.existsById(dataTopic.courseId())) {
            throw new ValidationException("No existe un curso con el id proporcionado.");
        }

        if(!userRepository.existsById(dataTopic.userId())) {
            throw new ValidationException("No existe un usuario con el id proporcionado.");
        }

        var course = courseRepository.getReferenceById(dataTopic.courseId());
        var user = userRepository.getReferenceById(dataTopic.userId());
        var topic = new Topic(
                null,
                LocalDate.now(),
                user,
                course,
                dataTopic.title(),
                dataTopic.message(),
                "abierto"
                );

        topicRepository.save(topic);
        return new DataDetailTopic(topic);
    }
}
