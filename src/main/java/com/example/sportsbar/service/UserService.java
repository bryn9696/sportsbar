package com.example.sportsbar.service;

import com.example.sportsbar.model.User;
import com.example.sportsbar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, BCryptPasswordEncoder bCryptPasswordEncoder1) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder1;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String email, String password) {
        if (userRepository.findByUsername(username).isPresent() || userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Username or email already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password)); // Encrypt password

        return userRepository.save(user);
    }

    public boolean authenticateUser(String username, String rawPassword) {
        // Retrieve user from the database
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Log details for debugging
        System.out.println("Authenticating user: " + username);
        System.out.println("Raw password: " + rawPassword);
        System.out.println("Stored hashed password: " + user.getPassword());

        // Compare passwords
        boolean matches = passwordEncoder.matches(rawPassword, user.getPassword());
        System.out.println("Password match result: " + matches);

        return matches;
    }


}
