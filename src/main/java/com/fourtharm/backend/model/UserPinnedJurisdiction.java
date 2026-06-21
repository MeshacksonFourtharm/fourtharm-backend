package com.fourtharm.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_pinned_jurisdictions")
public class UserPinnedJurisdiction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String jurisdictionType;

    @Column(nullable = false)
    private Long jurisdictionId;

    @Column(nullable = false)
    private Integer pinOrder;

    public UserPinnedJurisdiction() {
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getJurisdictionType() {
        return jurisdictionType;
    }

    public Long getJurisdictionId() {
        return jurisdictionId;
    }

    public Integer getPinOrder() {
        return pinOrder;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setJurisdictionType(String jurisdictionType) {
        this.jurisdictionType = jurisdictionType;
    }

    public void setJurisdictionId(Long jurisdictionId) {
        this.jurisdictionId = jurisdictionId;
    }

    public void setPinOrder(Integer pinOrder) {
        this.pinOrder = pinOrder;
    }
}