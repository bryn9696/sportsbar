package com.example.sportsbar.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Integer topic_id;

    private String name;

    @ManyToMany(mappedBy = "topics")
    private Set<Post> posts = new HashSet<>();

    // Getters and setters
    public Integer getId() {
        return topic_id;
    }

    public void setId(Integer id) {
        this.topic_id = topic_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
