package com.fourtharm.backend.dto;

public class AuthResponse {

private Long id;

private String nickname;

private String email;

private String role;

private Boolean verified;

private String county;

private String constituency;

private String ward;

public AuthResponse(
        Long id,
        String nickname,
        String email,
        String role,
        Boolean verified,
        String county,
        String constituency,
        String ward
) {
    this.id = id;
    this.nickname = nickname;
    this.email = email;
    this.role = role;
    this.verified = verified;
    this.county = county;
    this.constituency = constituency;
    this.ward = ward;
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

public String getCounty() {
    return county;
}

public String getConstituency() {
    return constituency;
}

public String getWard() {
    return ward;
}

}