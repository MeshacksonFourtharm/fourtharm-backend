package com.fourtharm.backend.controller;

import com.fourtharm.backend.dto.AdminPinRequest;
import com.fourtharm.backend.dto.AdminUserDto;
import com.fourtharm.backend.dto.ApiResponse;
import com.fourtharm.backend.dto.RoleUpdateRequest;
import com.fourtharm.backend.model.User;
import com.fourtharm.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

private final UserRepository userRepository;

private static final String SUPER_ADMIN_EMAIL =
        "brianmeshack819@gmail.com";

public AdminController(
        UserRepository userRepository
) {
    this.userRepository = userRepository;
}

@GetMapping("/test")
public String test() {
    return "Admin controller works";
}

@GetMapping("/users")
public List<AdminUserDto> getUsers() {

    return userRepository
            .findAll()
            .stream()
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
            .collect(Collectors.toList());
}

@PostMapping("/verify-pin")
public ApiResponse<Boolean> verifyPin(
        @RequestBody AdminPinRequest request
) {

    User user =
            userRepository.findByEmail(
                    request.getEmail()
            );

    if (user == null) {

        return new ApiResponse<>(
                false,
                "User not found",
                false
        );
    }

    if (
            user.getAdminPin() == null ||
            user.getAdminPin().isBlank()
    ) {

        return new ApiResponse<>(
                false,
                "Admin PIN not configured",
                false
        );
    }

    boolean valid =
            user.getAdminPin()
                    .equals(
                            request.getPin()
                    );

    if (valid) {

        user.setAdminVerified(true);

        user.setAdminVerifiedAt(
                System.currentTimeMillis()
        );

        userRepository.save(user);
    }

    return new ApiResponse<>(
            valid,
            valid
                    ? "PIN verified"
                    : "Invalid PIN",
            valid
    );
}

@GetMapping("/admin-session/{userId}")
public Boolean isAdminVerified(
        @PathVariable Long userId
) {

    User user =
            userRepository.findById(
                    userId
            ).orElse(null);

    if (user == null) {
        return false;
    }

    if (
            user.getAdminVerifiedAt()
                    == null
    ) {
        return false;
    }

    long age =
            System.currentTimeMillis()
                    - user.getAdminVerifiedAt();

    long oneHour =
            60 * 60 * 1000;

    if (age > oneHour) {

        user.setAdminVerified(false);

        user.setAdminVerifiedAt(null);

        userRepository.save(user);

        return false;
    }

    return Boolean.TRUE.equals(
            user.getAdminVerified()
    );
}

@PostMapping("/logout-admin/{userId}")
public ApiResponse<String> logoutAdmin(
        @PathVariable Long userId
) {

    User user =
            userRepository.findById(
                    userId
            ).orElse(null);

    if (user == null) {

        return new ApiResponse<>(
                false,
                "User not found",
                null
        );
    }

    user.setAdminVerified(false);

    user.setAdminVerifiedAt(null);

    userRepository.save(user);

    return new ApiResponse<>(
            true,
            "Admin session ended",
            null
    );
}

@PostMapping("/set-pin")
public ApiResponse<String> setAdminPin(
        @RequestBody AdminPinRequest request
) {

    User user =
            userRepository.findByEmail(
                    request.getEmail()
            );

    if (user == null) {

        return new ApiResponse<>(
                false,
                "User not found",
                null
        );
    }

    user.setAdminPin(
            request.getPin()
    );

    userRepository.save(user);

    return new ApiResponse<>(
            true,
            "PIN updated",
            null
    );
}

@PostMapping("/promote")
public ApiResponse<String> promoteUser(
        @RequestBody RoleUpdateRequest request
) {

    User superAdmin =
            userRepository.findByEmail(
                    SUPER_ADMIN_EMAIL
            );

    if (superAdmin == null) {

        return new ApiResponse<>(
                false,
                "Super Admin missing",
                null
        );
    }

    if (
            superAdmin.getAdminPin() == null ||
            !superAdmin.getAdminPin()
                    .equals(
                            request.getAdminPin()
                    )
    ) {

        return new ApiResponse<>(
                false,
                "Invalid Super Admin PIN",
                null
        );
    }

    User target =
            userRepository.findById(
                    request.getUserId()
            ).orElse(null);

    if (target == null) {

        return new ApiResponse<>(
                false,
                "Target user not found",
                null
        );
    }

    target.setRole(
            request.getRole()
    );

    if (
            "VERIFIED_CITIZEN"
                    .equals(
                            request.getRole()
                    )
    ) {

        target.setVerified(true);
    }

    userRepository.save(target);

    return new ApiResponse<>(
            true,
            "Role updated successfully",
            request.getRole()
    );
}

@PostMapping("/demote")
public ApiResponse<String> demoteUser(
        @RequestBody RoleUpdateRequest request
) {

    User superAdmin =
            userRepository.findByEmail(
                    SUPER_ADMIN_EMAIL
            );

    if (
            superAdmin == null ||
            superAdmin.getAdminPin() == null ||
            !superAdmin.getAdminPin()
                    .equals(
                            request.getAdminPin()
                    )
    ) {

        return new ApiResponse<>(
                false,
                "Invalid Super Admin PIN",
                null
        );
    }

    User target =
            userRepository.findById(
                    request.getUserId()
            ).orElse(null);

    if (target == null) {

        return new ApiResponse<>(
                false,
                "Target user not found",
                null
        );
    }

    target.setRole("CITIZEN");

    userRepository.save(target);

    return new ApiResponse<>(
            true,
            "User demoted",
            "CITIZEN"
    );
}

}