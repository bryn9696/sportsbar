package com.example.sportsbar.model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @Column(name = "user_id") // userId is the primary key
    private Integer userId;


    @Column(name = "post_id", nullable = false)
    private Integer postId;

    @NotNull
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "sport")
    private String sport;

    @Column(name = "media_type")
    private String mediaType;

    @Column(name = "media_url")
    private String mediaUrl;

    @Column(name = "voice_duration")
    private Integer voiceDuration;
    private String timestamp;
//    @Column(name = "created_at", updatable = false)
////    @CreationTimestamp
//    private LocalDateTime createdAt;
//    @Column(name = "updated_at", updatable = false)
//    private LocalDateTime updatedAt;
//
//    @PrePersist
//    protected void onCreate() {
//        this.createdAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        this.updatedAt = LocalDateTime.now();
//    }

    public Post() {
    }

    public Post(Integer userId, Integer postId, String content, String sport, String mediaType, String mediaUrl,
                String timestamp, Integer voiceDuration, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.sport = sport;
        this.mediaType = mediaType;
        this.mediaUrl = mediaUrl;
        this.voiceDuration = voiceDuration;
        this.timestamp = timestamp;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public Integer getVoiceDuration() {
        return voiceDuration;
    }

    public void setVoiceDuration(Integer voiceDuration) {
        this.voiceDuration = voiceDuration;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }

    @Override
    public String toString() {
        return "Post{" +
                ", userId='" + userId + '\'' +
                ", postId='" + postId + '\'' +
                ", content='" + content + '\'' +
                ", sport='" + sport + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", mediaUrl='" + mediaUrl + '\'' +
                ", timestamp=" + timestamp +
                ", voiceDuration=" + voiceDuration +
                '}';
    }
}
