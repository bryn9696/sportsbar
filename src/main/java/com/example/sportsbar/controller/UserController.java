package com.example.sportsbar.controller;

import com.example.sportsbar.config.JWTUtil;
import com.example.sportsbar.dto.AuthResponse;
import com.example.sportsbar.dto.LoginRequest;
import com.example.sportsbar.model.Post;
import com.example.sportsbar.model.User;
import com.example.sportsbar.repository.PostRepository;
import com.example.sportsbar.repository.UserRepository;
import com.example.sportsbar.service.AuthService;
import com.example.sportsbar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PostRepository postRepository, UserRepository userRepository, AuthService authService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            // Check if the username already exists
            if (userRepository.existsByUsername(user.getUsername())) {
                throw new IllegalArgumentException("Username already exists");
            }

            // Save the new user
            User registeredUser = userService.registerUser(
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @Transactional
    public User registerUser(String username, String email, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password)); // Encrypt password
        return userRepository.save(user);
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            boolean isAuthenticated = userService.authenticateUser(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            );
            if (isAuthenticated) {
                // Fetch user and their posts
                User user = userRepository.findByUsername(loginRequest.getUsername())
                        .orElseThrow(() -> new IllegalArgumentException("User not found"));
                List<Post> userPosts = postRepository.findByUser_Id(user.getId());

                // Generate the JWT token
                String token = JWTUtil.generateToken(user.getUsername());
                if (token == null) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Token generation failed");
                }

                // Build the response object
                AuthResponse authResponse = new AuthResponse();
                authResponse.setMessage("Login successful!");
                authResponse.setToken(token);
                authResponse.setPosts(userPosts);

                return ResponseEntity.ok(authResponse);  // Return the token and posts
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // (this should be a JWT or session in a real app)
    private String generateToken(String username) {
        // Replace with actual JWT or secure token logic
        return "dummy-token-for-" + username;
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getPostsForUser(@RequestParam Integer userId) {
        List<Post> posts = postRepository.findByUser_Id(userId);
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody Post post, @RequestParam User userId) {
        post.setUser(userId);
        Post savedPost = postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }
}

