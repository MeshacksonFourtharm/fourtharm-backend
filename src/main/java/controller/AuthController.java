package com.fourtharm.backend.controller;

import com.fourtharm.backend.dto.ApiResponse;
import com.fourtharm.backend.dto.AuthResponse;
import com.fourtharm.backend.dto.LoginRequest;
import com.fourtharm.backend.dto.RegisterRequest;
import com.fourtharm.backend.model.User;
import com.fourtharm.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

private final UserRepository repository;

public AuthController(
        UserRepository repository
) {
    this.repository = repository;
}

@PostMapping("/register")
public ApiResponse<AuthResponse> register(
        @RequestBody RegisterRequest request
) {

    if (repository.existsByEmail(
            request.getEmail()
    )) {

        return new ApiResponse<>(
                false,
                "Email already exists",
                null
        );
    }

    if (repository.existsByNationalId(
            request.getNationalId()
    )) {

        return new ApiResponse<>(
                false,
                "National ID already registered",
                null
        );
    }

    User user = new User(
            request.getNickname(),
            request.getEmail(),
            request.getPassword(),
            request.getNationalId()
    );

    user.setCounty(
            request.getCounty()
    );

    user.setConstituency(
            request.getConstituency()
    );

    user.setWard(
            request.getWard()
    );

    repository.save(user);

    AuthResponse response =
        new AuthResponse(
                user.getId(),
                user.getNickname(),
                user.getEmail(),
                user.getRole(),
                user.getVerified(),
                user.getCounty(),
                user.getConstituency(),
                user.getWard()
        );

    return new ApiResponse<>(
            true,
            "Account created successfully",
            response
    );
}

@PostMapping("/login")
public ApiResponse<AuthResponse> login(
        @RequestBody LoginRequest request
) {

    User user =
            repository.findByEmail(
                    request.getEmail()
            );

    if (user == null) {

        return new ApiResponse<>(
                false,
                "Account not found",
                null
        );
    }

    if (!user.getPassword()
            .equals(request.getPassword())) {

        return new ApiResponse<>(
                false,
                "Invalid password",
                null
        );
    }

    AuthResponse response =
        new AuthResponse(
                user.getId(),
                user.getNickname(),
                user.getEmail(),
                user.getRole(),
                user.getVerified(),
                user.getCounty(),
                user.getConstituency(),
                user.getWard()
        );

    return new ApiResponse<>(
            true,
            "Login successful",
            response
    );
}

}