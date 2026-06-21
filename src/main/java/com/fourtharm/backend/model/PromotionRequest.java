package com.fourtharm.backend.model;

public class PromotionRequest {

    private Long userId;

    private String role;

    public PromotionRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRole(String role) {
        this.role = role;
    }
}