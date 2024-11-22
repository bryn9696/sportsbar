package com.example.sportsbar.service;

import com.example.sportsbar.model.Post;
import com.example.sportsbar.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        // Logic for generating a unique postId
        // This could be based on the highest existing postId or another strategy

        Integer highestPostId = postRepository.findMaxPostId();  // Fetch the max postId from the database
        return highestPostId != null ? highestPostId + 1 : 1;  // Increment by 1 or start from 1
    }
}
