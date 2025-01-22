package com.example.sportsbar.service;

import com.example.sportsbar.model.Post;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.util.Properties;
import java.util.Arrays;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import java.util.Arrays;
import java.util.Properties;

@Service
public class PostConsumer {
    @KafkaListener(topics = "sports-football", groupId = "sportsbar-consumer-group")
    public void consumeFootballPosts(Post post) {
        System.out.println("Football post: " + post.getContent());
    }
//    // Set up consumer configuration
//    Properties properties = new Properties();
//    properties.put("bootstrap.servers", "localhost:9092");  // Kafka server address
//    properties.put("group.id", "test-group");  // Consumer group ID
//    properties.put("key.deserializer", StringDeserializer .class.getName());
//    properties.put("value.deserializer", StringDeserializer.class.getName());
//
//    // Create Kafka consumer instance
//    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
//
//    // Subscribe to the topic
//    consumer.subscribe(Arrays.asList("sports-football"));
//
//    // Consume messages from Kafka
//    try {
//        while (true) {
//            // Poll for new messages (blocks until messages are available)
//            var records = consumer.poll(1000);  // 1000ms timeout to avoid busy waiting
//            for (ConsumerRecord<String, String> record : records) {
//                // Print out the message received from Kafka
//                System.out.printf("Consumed message: Key = %s, Value = %s%n", record.key(), record.value());
//            }
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    } finally {
//        // Close the consumer connection
//        consumer.close();
//    }
}
