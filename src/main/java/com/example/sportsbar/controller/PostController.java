package com.example.sportsbar.controller;

import com.example.sportsbar.model.Post;
import com.example.sportsbar.repository.PostRepository;
import com.example.sportsbar.service.PostProducer;
import com.example.sportsbar.service.PostService;
import com.example.sportsbar.util.ContentValidator;
import com.example.sportsbar.util.SportDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/posts")
public class PostController {

    private final PostProducer postProducer;
    private final PostRepository postRepository;
    private final PostService postService;

    @Autowired
    public PostController(PostService postService, PostProducer postProducer, PostRepository postRepository) {
        this.postProducer = postProducer;
        this.postRepository = postRepository;
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> submitPost(@RequestBody Post post) {
        try {
            // Generate a unique postId before saving the post
            Integer postId = postService.generateUniquePostId();
            post.setPostId(postId);  // Set the generated postId manually
            post.setUserId(1);
            // Validate content (assuming ContentValidator is defined elsewhere)
            ContentValidator.validate(post);

            // Save the post to the database
            Post savedPost = postRepository.save(post);
            return ResponseEntity.ok(savedPost);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private Integer generatePostId() {
        // Logic to generate a unique postId
        return new Random().nextInt(1000);
    }

    @GetMapping
    public List<Post> getAllPosts() {

        return postRepository.findAll();
    }
}
