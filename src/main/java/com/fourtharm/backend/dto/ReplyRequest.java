package com.fourtharm.backend.dto;

public class ReplyRequest {

private Long postId;

private Long userId;

private String content;

private Long parentReplyId;

public ReplyRequest() {
}

public Long getPostId() {
    return postId;
}

public void setPostId(
        Long postId
) {
    this.postId = postId;
}

public Long getUserId() {
    return userId;
}

public void setUserId(
        Long userId
) {
    this.userId = userId;
}

public String getContent() {
    return content;
}

public void setContent(
        String content
) {
    this.content = content;
}

public Long getParentReplyId() {
    return parentReplyId;
}

public void setParentReplyId(
        Long parentReplyId
) {
    this.parentReplyId = parentReplyId;
}

}