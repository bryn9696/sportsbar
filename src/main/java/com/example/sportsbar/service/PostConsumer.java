package com.example.sportsbar.service;

import com.example.sportsbar.model.Post;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PostConsumer {
    @KafkaListener(topics = "sports_football", groupId = "sportsbar-consumer-group")
    public void consumeFootballPosts(Post post) {
        System.out.println("Football post: " + post.getContent());
    }

}
