package com.fourtharm.backend.dto;

public class RoleUpdateRequest {

    private Long userId;

    private String role;

    private String adminPin;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(
            Long userId
    ) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(
            String role
    ) {
        this.role = role;
    }

    public String getAdminPin() {
        return adminPin;
    }

    public void setAdminPin(
            String adminPin
    ) {
        this.adminPin = adminPin;
    }
}