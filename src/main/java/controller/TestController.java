package com.fourtharm.backend.controller;

import com.fourtharm.backend.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public ApiResponse<String> home() {

        return new ApiResponse<>(
                true,
                "Backend is running successfully",
                "FourthArm Backend Running"
        );
    }
}