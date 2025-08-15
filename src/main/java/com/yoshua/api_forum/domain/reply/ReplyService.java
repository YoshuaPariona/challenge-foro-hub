package com.yoshua.api_forum.domain.reply;

import com.yoshua.api_forum.domain.topic.TopicRepository;
import com.yoshua.api_forum.domain.user.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    public DataDetailReply registerReply(DataRegisterReply dataReply) {
        if(!topicRepository.existsById(dataReply.topicId())) {
            throw new ValidationException("No existe un tópico con el id proporcionado.");
        }

        if(!userRepository.existsById(dataReply.userId())) {
            throw new ValidationException("No existe un usuario con el id proporcionado.");
        }

        var topic = topicRepository.getReferenceById(dataReply.topicId());
        var user = userRepository.getReferenceById(dataReply.userId());
        var reply = new Reply(
                null,
                LocalDate.now(),
                topic,
                user,
                dataReply.message(),
                false
        );

        replyRepository.save(reply);
        return new DataDetailReply(reply);
    }
}
