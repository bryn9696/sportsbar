package com.example.sportsbar.util;

import com.example.sportsbar.model.Post;

public class ContentValidator {
    public static void validate(Post post) {
        System.out.println("Validating post: " + post);
        if (post.getContent() == null || post.getContent().isBlank()) {
            throw new IllegalArgumentException("Content cannot be null or blank");
        }

        if (post.getSport() == null || post.getSport().isBlank()) {
            throw new IllegalArgumentException("Sport cannot be null or blank");
        }

        switch (post.getMediaType()) {
            case "text":
                if (post.getContent().length() > 200) {
                    throw new IllegalArgumentException("Text content exceeds 200 characters");
                }
                break;
            case "image":
            case "video":
                if (post.getContent().length() > 200 || post.getMediaUrl() == null) {
                    throw new IllegalArgumentException("Image/Video content or media URL is invalid");
                }
                break;
            case "voice":
                if (post.getContent().length() > 200 || post.getVoiceDuration() > 120) {
                    throw new IllegalArgumentException("Voice content exceeds limits");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid media type");
        }
    }
}
