package com.fourtharm.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ForumReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String nickname;

    private String profilePhoto;

    private Boolean verified;

    private String role;

    @Column(length = 5000)
    private String content;

    private LocalDateTime createdAt =
            LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference("post-replies")
    private ForumPost forumPost;

    @ManyToOne
    @JoinColumn(name = "parent_reply_id")
    @JsonBackReference("reply-parent")
    private ForumReply parentReply;

    @OneToMany(
            mappedBy = "parentReply",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference("reply-parent")
    private List<ForumReply> childReplies =
            new ArrayList<>();

    public ForumReply() {
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public Boolean getVerified() {
        return verified;
    }

    public String getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public ForumPost getForumPost() {
        return forumPost;
    }

    public ForumReply getParentReply() {
        return parentReply;
    }

    public List<ForumReply> getChildReplies() {
        return childReplies;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setForumPost(
            ForumPost forumPost
    ) {
        this.forumPost = forumPost;
    }

    public void setParentReply(
            ForumReply parentReply
    ) {
        this.parentReply = parentReply;
    }

    public void setChildReplies(
            List<ForumReply> childReplies
    ) {
        this.childReplies = childReplies;
    }
}