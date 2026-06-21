package com.fourtharm.backend.dto;

public class UpdateProfileRequest {

    private String nickname;

    private String phoneNumber;

    private String county;

    private String constituency;

    private String ward;

    private String bio;

    public UpdateProfileRequest() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(
            String nickname
    ) {
        this.nickname = nickname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(
            String phoneNumber
    ) {
        this.phoneNumber = phoneNumber;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(
            String county
    ) {
        this.county = county;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(
            String constituency
    ) {
        this.constituency = constituency;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(
            String ward
    ) {
        this.ward = ward;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(
            String bio
    ) {
        this.bio = bio;
    }
}