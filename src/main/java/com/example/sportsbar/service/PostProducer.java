package com.example.sportsbar.service;

import com.example.sportsbar.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PostProducer {
    private final KafkaTemplate<String, Post> kafkaTemplate;

    @Autowired
    public PostProducer(KafkaTemplate<String, Post> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPost(String topic, Post post) {
        kafkaTemplate.send(topic, post);
    }
}
