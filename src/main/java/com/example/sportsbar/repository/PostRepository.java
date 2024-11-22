package com.example.sportsbar.repository;

import com.example.sportsbar.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    // Get the highest postId
    @Query("SELECT MAX(p.postId) FROM Post p")
    Integer findMaxPostId();  // Fetch max postId

    List<Post> findByUserId(Integer userId);
}

