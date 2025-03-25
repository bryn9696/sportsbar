package com.example.sportsbar.repository;

import com.example.sportsbar.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT MAX(p.postId) FROM Post p")
    Integer findMaxPostId();

    List<Post> findByUser_Id(Integer userId);

    List<Post> findByTopics_Name(String topicName);

    List<Post> findByUser_IdAndTopics_Name(Integer userId, String topicName);

}

