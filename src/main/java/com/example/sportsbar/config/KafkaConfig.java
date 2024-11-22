package com.example.sportsbar.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic footballTopic() {
        return TopicBuilder.name("sports_football").build();
    }

    @Bean
    public NewTopic basketballTopic() {
        return TopicBuilder.name("sports_basketball").build();
    }

    // Add more topics as needed
}
