package com.fourtharm.backend.dto;

public class AdminPinResponse {

    private boolean success;

    private String role;

    public AdminPinResponse(
            boolean success,
            String role
    ) {
        this.success = success;
        this.role = role;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getRole() {
        return role;
    }
}