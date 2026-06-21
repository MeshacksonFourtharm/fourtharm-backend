package com.fourtharm.backend.model;

import jakarta.persistence.*;

@Entity
@Table(
uniqueConstraints = {
@UniqueConstraint(
columnNames = {
"forum_post_id",
"userId"
}
)
}
)
public class PostDislike {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private Long userId;

@ManyToOne
@JoinColumn(name = "forum_post_id")
private ForumPost forumPost;

public PostDislike() {
}

public PostDislike(
        Long userId,
        ForumPost forumPost
) {
    this.userId = userId;
    this.forumPost = forumPost;
}

public Long getId() {
    return id;
}

public Long getUserId() {
    return userId;
}

public ForumPost getForumPost() {
    return forumPost;
}

public void setUserId(Long userId) {
    this.userId = userId;
}

public void setForumPost(
        ForumPost forumPost
) {
    this.forumPost = forumPost;
}

}