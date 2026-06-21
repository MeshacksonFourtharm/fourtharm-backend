package com.fourtharm.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "forum_post")
public class ForumPost {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

/*
 * Hidden system identity
 */
private Long userId;

/*
 * Visible identity
 */
private String nickname;

/*
 * Profile picture URL/path
 * Example:
 * uploads/profiles/user123.jpg
 */
private String profilePhoto;

/*
 * Verification badge
 */
private Boolean verified = false;

/*
 * CITIZEN
 * VERIFIED_CITIZEN
 * MCA
 * MP
 * GOVERNOR
 * ADMIN
 */
private String role = "CITIZEN";

@Column(length = 3000)
private String content;

private String governanceLevel;

private String county;

private String constituency;

private String ward;

private Long likesCount = 0L;

private Long repliesCount = 0L;

private long dislikesCount = 0L;

private Boolean pinned = false;

private Long pinnedAt;

public Boolean getPinned() {
    return pinned;
}

public Long getPinnedAt() {
    return pinnedAt;
}

public void setPinned(Boolean pinned) {
    this.pinned = pinned;
}

public void setPinnedAt(Long pinnedAt) {
    this.pinnedAt = pinnedAt;
}

public ForumPost() {
}

public ForumPost(
        Long userId,
        String nickname,
        String profilePhoto,
        Boolean verified,
        String role,
        String content,
        String governanceLevel,
        String county,
        String constituency,
        String ward
) {
    this.userId = userId;
    this.nickname = nickname;
    this.profilePhoto = profilePhoto;
    this.verified = verified;
    this.role = role;
    this.content = content;
    this.governanceLevel = governanceLevel;
    this.county = county;
    this.constituency = constituency;
    this.ward = ward;
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

public String getGovernanceLevel() {
    return governanceLevel;
}

public String getCounty() {
    return county;
}

public String getConstituency() {
    return constituency;
}

public String getWard() {
    return ward;
}

public Long getLikesCount() {
    return likesCount;
}

public long getDislikesCount() {
    return dislikesCount;
}

public Long getRepliesCount() {
    return repliesCount;
}

public void setId(Long id) {
    this.id = id;
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

public void setGovernanceLevel(String governanceLevel) {
    this.governanceLevel = governanceLevel;
}

public void setCounty(String county) {
    this.county = county;
}

public void setConstituency(String constituency) {
    this.constituency = constituency;
}

public void setWard(String ward) {
    this.ward = ward;
}

public void setLikesCount(Long likesCount) {
    this.likesCount = likesCount;
}

public void setRepliesCount(Long repliesCount) {
    this.repliesCount = repliesCount;
}

public void setDislikesCount(
        long dislikesCount
) {
    this.dislikesCount =
            dislikesCount;
}

}