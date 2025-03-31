package com.example.sportsbar.dto;

import com.example.sportsbar.model.Topic;

import java.util.List;

public class PostRequest {
    private String content;
    private String mediaType;
    private String mediaUrl;
    private List<Topic> topic;
    private List<Long> topicIds; // List of topic IDs

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public List<Topic> getTopic() {
        return topic;
    }

    public void setTopic(List<Topic> topic) {
        this.topic = topic;
    }

    public List<Long> getTopicIds() {
        return topicIds;
    }

    public void setTopicIds(List<Long> topicIds) {
        this.topicIds = topicIds;
    }

    public Iterable<Long> getTopicId() { return topicIds;
    }
}
