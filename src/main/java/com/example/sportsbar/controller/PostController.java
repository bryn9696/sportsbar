package com.example.sportsbar.controller;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.sportsbar.model.Post;
import com.example.sportsbar.model.Topic;
import com.example.sportsbar.model.User;
import com.example.sportsbar.repository.PostRepository;
import com.example.sportsbar.dto.PostRequest;
import com.example.sportsbar.repository.TopicRepository;
import com.example.sportsbar.repository.UserRepository;
import com.example.sportsbar.service.JwtService;
import com.example.sportsbar.service.PostProducer;
import com.example.sportsbar.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostRepository postRepository;
    private final PostService postService;
    private final UserRepository userRepository;

    @Autowired
    public PostController(PostRepository postRepository, PostService postService, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.postService = postService;
        this.userRepository = userRepository;
    }

    @PostMapping("/submitpost")
    public ResponseEntity<?> submitPost(@RequestBody Post post) {
        System.out.println("Post submitted: " + post.getContent());
        return ResponseEntity.ok("Post submitted successfully");
    }

    @GetMapping("/allposts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/by-user")
    public ResponseEntity<List<Post>> getPostsByUser(@RequestParam Integer userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/by-topic")
    public ResponseEntity<List<Post>> getPostsByTopic(@RequestParam String topicName) {
        List<Post> posts = postRepository.findByTopics_Name(topicName);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/by-user-and-topic")
    public ResponseEntity<List<Post>> getPostsByUserAndTopic(@RequestParam Integer userId, @RequestParam String topicName) {
        List<Post> posts = postRepository.findByUserIdAndTopics_Name(userId, topicName);
        return ResponseEntity.ok(posts);
    }
}
