package com.example.sportsbar.controller;

import com.example.sportsbar.dto.AuthResponse;
import com.example.sportsbar.dto.LoginRequest;
import com.example.sportsbar.model.Post;
import com.example.sportsbar.model.User;
import com.example.sportsbar.repository.PostRepository;
import com.example.sportsbar.service.AuthService;
import com.example.sportsbar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PostRepository postRepository;
    private final AuthService authService;

    @Autowired
    public UserController(UserService userService, PostRepository postRepository, AuthService authService) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.authService = authService;
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword()
            );
            return ResponseEntity.ok("User registered successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Login user
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = authService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());

        if (isAuthenticated) {
            // Generate and return a token or session
            String token = generateToken(loginRequest.getUsername());
            return ResponseEntity.ok(new AuthResponse("Login successful", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    // Example of generating a token (this should be a JWT or session in a real app)
    private String generateToken(String username) {
        // Replace with actual JWT or secure token logic
        return "dummy-token-for-" + username;
    }

    // Get all posts for the logged-in user
    @GetMapping("/posts")
    public ResponseEntity<?> getPostsForUser(@RequestParam Integer userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        return ResponseEntity.ok(posts);
    }

    // Create a new post for the logged-in user
    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody Post post, @RequestParam Integer userId) {
        post.setUserId(userId);  // Ensure the post belongs to the logged-in user
        Post savedPost = postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }
}

