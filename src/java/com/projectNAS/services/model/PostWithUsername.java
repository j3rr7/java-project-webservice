package com.projectNAS.services.model;

/**
 *
 * @author _
 */
public class PostWithUsername {
    private Post post;
    private String username;
    private int likes;
    private int replies;

    public PostWithUsername(Post post, String username, int likes, int replies) {
        this.post = post;
        this.username = username;
        this.likes = likes;
        this.replies = replies;
    }
    
    public PostWithUsername(Post post, String username) {
        this.post = post;
        this.username = username;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }
    
    
}
