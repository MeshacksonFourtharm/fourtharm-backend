package com.fourtharm.backend.controller;

import com.fourtharm.backend.dto.ApiResponse;
import com.fourtharm.backend.model.Constituency;
import com.fourtharm.backend.service.ConstituencyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/constituencies")
public class ConstituencyController {

private final ConstituencyService constituencyService;

public ConstituencyController(
        ConstituencyService constituencyService
) {
    this.constituencyService =
            constituencyService;
}

@GetMapping
public ApiResponse<List<Constituency>>
getAllConstituencies() {

    return new ApiResponse<>(
            true,
            "All constituencies fetched successfully",
            constituencyService.getAllConstituencies()
    );
}

@GetMapping("/county/{countyId}")
public ApiResponse<List<Constituency>>
getByCounty(
        @PathVariable Long countyId
) {

    return new ApiResponse<>(
            true,
            "Constituencies fetched successfully",
            constituencyService
                    .getConstituenciesByCounty(
                            countyId
                    )
    );
}

}