package com.fourtharm.backend.controller;

import com.fourtharm.backend.dto.PinJurisdictionRequest;
import com.fourtharm.backend.model.UserPinnedJurisdiction;
import com.fourtharm.backend.service.UserPinnedJurisdictionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pins")
public class UserPinnedJurisdictionController {

    private final UserPinnedJurisdictionService service;

    public UserPinnedJurisdictionController(
            UserPinnedJurisdictionService service
    ) {
        this.service = service;
    }

    @GetMapping
    public List<UserPinnedJurisdiction> getPins(

            @RequestParam Long userId,

            @RequestParam String jurisdictionType
    ) {

        return service.getPins(
                userId,
                jurisdictionType
        );
    }

    @PostMapping
    public UserPinnedJurisdiction pinJurisdiction(

            @RequestBody
            PinJurisdictionRequest request
    ) {

        return service.pinJurisdiction(
                request.getUserId(),
                request.getJurisdictionType(),
                request.getJurisdictionId()
        );
    }

    @DeleteMapping
    public void unpinJurisdiction(

            @RequestBody
            PinJurisdictionRequest request
    ) {

        service.unpinJurisdiction(
                request.getUserId(),
                request.getJurisdictionType(),
                request.getJurisdictionId()
        );
    }
}