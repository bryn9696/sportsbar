package com.example.sportsbar.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.sportsbar.model.Post;
import com.example.sportsbar.model.Topic;
import com.example.sportsbar.model.User;
import com.example.sportsbar.repository.PostRepository;
import com.example.sportsbar.dto.PostRequest;
import com.example.sportsbar.repository.TopicRepository;
import com.example.sportsbar.repository.UserRepository;
import com.example.sportsbar.service.PostProducer;
import com.example.sportsbar.service.PostService;

import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import com.auth0.jwt.JWT;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/posts")
public class PostController {

    private final PostProducer postProducer;
    private final PostRepository postRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final PostService postService;

    @Autowired
    public PostController(PostService postService, PostProducer postProducer, PostRepository postRepository, TopicRepository topicRepository, UserRepository userRepository) {
        this.postProducer = postProducer;
        this.postRepository = postRepository;
        this.postService = postService;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> submitPost(
            @RequestBody PostRequest postRequest,
            @RequestHeader("Authorization") String authorization) {
        try {
            // Check if the Authorization header exists and starts with "Bearer "
            if (authorization == null || !authorization.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is missing or malformed");
            }

            String token = authorization.substring(7);  // Extract the token

            // Decode the JWT token (no verification, only extracting claims)
            DecodedJWT decodedJWT = JWT.decode(token);
            String username = decodedJWT.getSubject();  // Extract username (from `sub` claim)

            // Fetch the user from the database
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            // Convert topic IDs to actual Topic objects
            List<Topic> topics = topicRepository.findAllById(postRequest.getTopicIds());

            // Create and save the post
            Post post = new Post();
            post.setContent(postRequest.getContent());
            post.setMediaType(postRequest.getMediaType());
            post.setMediaUrl(postRequest.getMediaUrl());
            post.setUser(user);
            post.setTopic(topics);  // Use Set to prevent duplicates
            post.setTimestamp(LocalDateTime.now());

            Post savedPost = postRepository.save(post);
            return ResponseEntity.ok(savedPost);

        } catch (Exception e) {
            System.err.println("Error processing post: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing token");
        }
    }

//    @GetMapping("/allposts")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public ResponseEntity<List<Post>> getAllPosts(Authentication authentication) {
//        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
//        System.out.println("User ID: " + userPrincipal.getName()); // Check if the user is correctly authenticated
//
//        List<Post> posts = postService.getAllPosts();
//
//        return ResponseEntity.ok(posts);
//    }

    @GetMapping("/allposts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/by-user")
    public ResponseEntity<List<Post>> getPostsByUser(@RequestParam Integer userId) {
        List<Post> posts = postRepository.findByUser_Id(userId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/by-topic")
    public ResponseEntity<List<Post>> getPostsByTopic(@RequestParam String topicName) {
        List<Post> posts = postRepository.findByTopics_Name(topicName);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/by-user-and-topic")
    public ResponseEntity<List<Post>> getPostsByUserAndTopic(@RequestParam Integer userId, @RequestParam String topicName) {
        List<Post> posts = postRepository.findByUser_IdAndTopics_Name(userId, topicName);
        return ResponseEntity.ok(posts);
    }
}
