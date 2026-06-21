package com.fourtharm.backend.dto;

public class DislikeRequest {

private Long postId;

private Long userId;

public DislikeRequest() {
}

public Long getPostId() {
    return postId;
}

public void setPostId(Long postId) {
    this.postId = postId;
}

public Long getUserId() {
    return userId;
}

public void setUserId(Long userId) {
    this.userId = userId;
}

}