package com.fourtharm.backend.controller;

import com.fourtharm.backend.dto.AdminUserDto;
import com.fourtharm.backend.model.User;
import com.fourtharm.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class CitizenVerificationController {

    private final UserRepository userRepository;

    public CitizenVerificationController(
            UserRepository userRepository
    ) {
        this.userRepository =
                userRepository;
    }

    @GetMapping(
            "/pending-citizens"
    )
    public List<AdminUserDto>
    pendingCitizens() {

        return userRepository
                .findAll()
                .stream()
                .filter(
                        user ->
                                !Boolean.TRUE.equals(
                                        user.getVerified()
                                )
                )
                .map(
                        user ->
                                new AdminUserDto(
                                        user.getId(),
                                        user.getNickname(),
                                        user.getEmail(),
                                        user.getRole(),
                                        user.getVerified(),
                                        user.getCounty(),
                                        user.getConstituency(),
                                        user.getWard()
                                )
                )
                .collect(
                        Collectors.toList()
                );
    }

    @PutMapping(
            "/verify-citizen/{id}"
    )
    public String verifyCitizen(

            @PathVariable
            Long id

    ) {

        User user =
                userRepository
                        .findById(id)
                        .orElseThrow();

        user.setVerified(
                true
        );

        userRepository.save(
                user
        );

        return "Citizen verified";
    }
}