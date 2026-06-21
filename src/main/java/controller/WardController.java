package com.fourtharm.backend.controller;

import com.fourtharm.backend.dto.ApiResponse;
import com.fourtharm.backend.model.Ward;
import com.fourtharm.backend.service.WardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wards")
public class WardController {

private final WardService wardService;

public WardController(
        WardService wardService
) {
    this.wardService =
            wardService;
}

@GetMapping
public ApiResponse<List<Ward>>
getAllWards() {

    return new ApiResponse<>(
            true,
            "All wards fetched successfully",
            wardService.getAllWards()
    );
}

@GetMapping("/constituency/{constituencyId}")
public ApiResponse<List<Ward>>
getByConstituency(
        @PathVariable Long constituencyId
) {

    return new ApiResponse<>(
            true,
            "Wards fetched successfully",
            wardService.getWardsByConstituency(
                    constituencyId
            )
    );
}

}