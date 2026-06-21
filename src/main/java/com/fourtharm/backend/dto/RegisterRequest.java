package com.fourtharm.backend.dto;

public class RegisterRequest {

private String nickname;

private String email;

private String password;

private String nationalId;

private String county;

private String constituency;

private String ward;

public RegisterRequest() {
}

public String getNickname() {
    return nickname;
}

public void setNickname(
        String nickname
) {
    this.nickname = nickname;
}

public String getEmail() {
    return email;
}

public void setEmail(
        String email
) {
    this.email = email;
}

public String getPassword() {
    return password;
}

public void setPassword(
        String password
) {
    this.password = password;
}

public String getNationalId() {
    return nationalId;
}

public void setNationalId(
        String nationalId
) {
    this.nationalId = nationalId;
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

}