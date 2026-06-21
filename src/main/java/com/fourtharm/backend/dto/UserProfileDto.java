package com.fourtharm.backend.dto;

public class UserProfileDto {

    private Long id;
    private String nickname;
    private String email;
    private String role;
    private Boolean verified;

    private String phoneNumber;
    private String county;
    private String constituency;
    private String ward;
    private String bio;

    private String profilePhoto;
    private String coverPhoto;

    public UserProfileDto(
            Long id,
            String nickname,
            String email,
            String role,
            Boolean verified,
            String phoneNumber,
            String county,
            String constituency,
            String ward,
            String bio,
            String profilePhoto,
            String coverPhoto
    ) {

        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
        this.verified = verified;
        this.phoneNumber = phoneNumber;
        this.county = county;
        this.constituency = constituency;
        this.ward = ward;
        this.bio = bio;

        this.profilePhoto = profilePhoto;
        this.coverPhoto = coverPhoto;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public Boolean getVerified() {
        return verified;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

    public String getBio() {
        return bio;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }
}