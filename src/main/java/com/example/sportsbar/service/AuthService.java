package com.example.sportsbar.service;

import com.example.sportsbar.model.User;
import com.example.sportsbar.repository.PostRepository;
import com.example.sportsbar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean authenticateUser(String username, String rawPassword) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return false; // User does not exist
        }

        User user = userOptional.get();

        // Validate the password
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

}
