package com.example.sportsbar.service;

import com.example.sportsbar.dto.PostRequest;
import com.example.sportsbar.model.Post;
import com.example.sportsbar.model.Topic;
import com.example.sportsbar.model.User;
import com.example.sportsbar.repository.PostRepository;
import org.apache.kafka.common.message.DescribeUserScramCredentialsRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post savePost(Post post) {
//        post.setCreatedAt(new Date());
//        post.setUpdatedAt(new Date());
        return postRepository.save(post);
    }

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // Method to generate a unique postId
    public Integer generateUniquePostId() {
        Integer highestPostId = postRepository.findMaxPostId();
        return highestPostId != null ? highestPostId + 1 : 1;
    }

    public Post submitPost(PostRequest postRequest) {
        List<Topic> topics = new ArrayList<>();
        Topic footballTopic = new Topic();;
        System.out.println(postRequest.getContent().toUpperCase());
        topics.add(footballTopic);
        Post post = new Post();
        post.getPostId();
        post.setContent(postRequest.getContent());
        post.setContent(postRequest.getContent());
        post.setTopic(topics);
        post.setMediaType(postRequest.getMediaType());
        post.setMediaUrl(postRequest.getMediaUrl());
        post.setTopicIds(postRequest.getTopicIds());
        return postRepository.save(post);
    }

}
