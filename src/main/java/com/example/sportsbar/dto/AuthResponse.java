package com.example.sportsbar.dto;

import com.example.sportsbar.model.Post;

import java.util.List;

public class AuthResponse {
    private String message;
    private String token;
    private List<Post> posts;

    public AuthResponse(String message, String token, List posts) {
        this.message = message;
        this.token = token;
        this.posts = posts;
    }


//    public AuthResponse(String string) {
//
//    }

    public AuthResponse() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List getPosts() {
        return posts;
    }

    public void setPosts(List posts) {
        this.posts = posts;
    }
}
