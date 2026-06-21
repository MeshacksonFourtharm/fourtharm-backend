package com.fourtharm.backend.controller;

import com.fourtharm.backend.dto.ApiResponse;
import com.fourtharm.backend.model.County;
import com.fourtharm.backend.service.CountyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/counties")
public class CountyController {

    private final CountyService countyService;

    public CountyController(CountyService countyService) {
        this.countyService = countyService;
    }

    @GetMapping
    public ApiResponse<List<County>> getAllCounties() {

        return new ApiResponse<>(
                true,
                "Counties fetched successfully",
                countyService.getAllCounties()
        );
    }
}