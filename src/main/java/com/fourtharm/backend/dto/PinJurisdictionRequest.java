package com.fourtharm.backend.dto;

public class PinJurisdictionRequest {

    private Long userId;

    private String jurisdictionType;

    private Long jurisdictionId;

    public Long getUserId() {
        return userId;
    }

    public String getJurisdictionType() {
        return jurisdictionType;
    }

    public Long getJurisdictionId() {
        return jurisdictionId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setJurisdictionType(
            String jurisdictionType
    ) {
        this.jurisdictionType = jurisdictionType;
    }

    public void setJurisdictionId(
            Long jurisdictionId
    ) {
        this.jurisdictionId = jurisdictionId;
    }
}