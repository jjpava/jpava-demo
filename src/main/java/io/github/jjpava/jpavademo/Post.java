package io.github.jjpava.jpavademo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long postId;

    private String postTitle;

    @Column(name = "content")
    private String postContent;

    public Post() {
    }

    public Post(String postTitle, String postContent) {
        this.postTitle = postTitle;
        this.postContent = postContent;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long id) {
        this.postId = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String title) {
        this.postTitle = title;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String content) {
        this.postContent = content;
    }
}
