package com.fourtharm.backend.dto;

public class AdminPinRequest {

private String email;

private String pin;

public AdminPinRequest() {
}

public String getEmail() {

    return email;
}

public void setEmail(
        String email
) {

    this.email = email;
}

public String getPin() {

    return pin;
}

public void setPin(
        String pin
) {

    this.pin = pin;
}

}