package com.fourtharm.backend.model;

import jakarta.persistence.*;
@Entity @Table(name = "users") public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String nickname;

@Column(unique = true)
private String email;

private String password;


@Column(unique = true)
private String nationalId;

private String phoneNumber;

private String county;

private String constituency;

private String ward;

@Column(length = 1000)
private String bio;

private String profilePhoto;

private String coverPhoto;

private String role = "CITIZEN";

private Boolean verified = false;

private Boolean active = true;

/*
 * Admin verification
 */

private String adminPin;

private Boolean adminVerified = false;

private Long adminVerifiedAt;

public User() {
}

public User(
        String nickname,
        String email,
        String password,
        String nationalId
) {
    this.nickname = nickname;
    this.email = email;
    this.password = password;
    this.nationalId = nationalId;
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

public String getPassword() {
    return password;
}

public String getNationalId() {
    return nationalId;
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

public String getAdminPin() {
    return adminPin;
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

public String getRole() {
    return role;
}

public Boolean getVerified() {
    return verified;
}

public Boolean getActive() {
    return active;
}

public void setNickname(String nickname) {
    this.nickname = nickname;
}

public void setEmail(String email) {
    this.email = email;
}

public void setPassword(String password) {
    this.password = password;
}

public void setNationalId(String nationalId) {
    this.nationalId = nationalId;
}

public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
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

public void setBio(String bio) {
    this.bio = bio;
}

public void setProfilePhoto(String profilePhoto) {
    this.profilePhoto = profilePhoto;
}

public void setCoverPhoto(String coverPhoto) {
    this.coverPhoto = coverPhoto;
}

public void setRole(String role) {
    this.role = role;
}

public void setVerified(Boolean verified) {
    this.verified = verified;
}

public void setActive(Boolean active) {
    this.active = active;
}

public Boolean getAdminVerified() {
    return adminVerified;
}

public void setAdminVerified(Boolean adminVerified) {
    this.adminVerified = adminVerified;
}

public Long getAdminVerifiedAt() {
    return adminVerifiedAt;
}

public void setAdminVerifiedAt(Long adminVerifiedAt) {
    this.adminVerifiedAt = adminVerifiedAt;
}

public void setAdminPin(String adminPin) {
    this.adminPin = adminPin;
}
}