package com.fourtharm.backend.controller;

import com.fourtharm.backend.model.User;
import com.fourtharm.backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin("*")
public class FileUploadController {

    private final UserRepository userRepository;

    public FileUploadController(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @PostMapping("/profile/{userId}")
    public ResponseEntity<String> uploadProfilePhoto(

            @PathVariable Long userId,

            @RequestParam("image")
            MultipartFile image

    ) {

        try {

    System.out.println(
            "PROFILE UPLOAD CALLED FOR USER "
            + userId
    );

    User user =
            userRepository.findById(
                    userId
            ).orElseThrow();

            String uploadDir =
        System.getProperty("user.dir")
        + File.separator
        + "uploads"
        + File.separator
        + "profile";

File directory =
        new File(uploadDir);

if (!directory.exists()) {
    directory.mkdirs();
}

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName =
                    UUID.randomUUID()
                    + "_"
                    + image.getOriginalFilename();

            File destination =
                    new File(
                            directory,
                            fileName
                    );
                    System.out.println(
        "Saving image to: "
        + destination.getAbsolutePath()
);

            image.transferTo(
                    destination
            );

            String imageUrl =
                    "http://192.168.88.69:8080/uploads/profile/"
                    + fileName;

            user.setProfilePhoto(
                    imageUrl
            );

            userRepository.save(
                    user
            );

            return ResponseEntity.ok(
                    imageUrl
            );

        } catch (Exception e) {

    e.printStackTrace();

    return ResponseEntity
            .badRequest()
            .body(
                    e.getMessage()
            );
}
    }

    @PostMapping("/cover/{userId}")
    public ResponseEntity<String> uploadCoverPhoto(

            @PathVariable Long userId,

            @RequestParam("image")
            MultipartFile image

    ) {

        try {

    System.out.println(
            "COVER UPLOAD CALLED FOR USER "
            + userId
    );

    User user =
            userRepository.findById(
                    userId
            ).orElseThrow();

            String uploadDir =
        System.getProperty("user.dir")
        + File.separator
        + "uploads"
        + File.separator
        + "cover";

File directory =
        new File(uploadDir);

if (!directory.exists()) {
    directory.mkdirs();
}

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName =
                    UUID.randomUUID()
                    + "_"
                    + image.getOriginalFilename();

            File destination =
                    new File(
                            directory,
                            fileName
                    );
                    System.out.println(
        "Saving image to: "
        + destination.getAbsolutePath()
);

            image.transferTo(
                    destination
            );

            String imageUrl =
                    "http://192.168.88.69:8080/uploads/cover/"
                    + fileName;

            user.setCoverPhoto(
                    imageUrl
            );

            userRepository.save(
                    user
            );

            return ResponseEntity.ok(
                    imageUrl
            );

        } catch (Exception e) {

    e.printStackTrace();

    return ResponseEntity
            .badRequest()
            .body(
                    e.getMessage()
            );
}
    }
}