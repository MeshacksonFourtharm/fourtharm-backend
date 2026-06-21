package com.fourtharm.backend.controller;

import com.fourtharm.backend.dto.UpdateProfileRequest;
import com.fourtharm.backend.dto.UserProfileDto;
import com.fourtharm.backend.model.User;
import com.fourtharm.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin("*")
public class ProfileController {

private final UserRepository repository;  

public ProfileController(  
        UserRepository repository  
) {  
    this.repository = repository;  
}  

@GetMapping("/{id}")  
public UserProfileDto getProfile(  
        @PathVariable Long id  
) {  

    User user =  
            repository.findById(id)  
                    .orElseThrow();  

    return buildDto(user);  
}  

@PutMapping("/{id}")  
public UserProfileDto updateProfile(  
        @PathVariable Long id,  
        @RequestBody UpdateProfileRequest request  
) {  

    User user =  
            repository.findById(id)  
                    .orElseThrow();  

    if (  
            request.getNickname() != null &&  
            !request.getNickname().trim().isEmpty()  
    ) {  

        user.setNickname(  
                request.getNickname().trim()  
        );  
    }  

    user.setPhoneNumber(  
            request.getPhoneNumber()  
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

    user.setBio(  
            request.getBio()  
    );  

    repository.save(user);  

    return buildDto(user);  
}  

private UserProfileDto buildDto(  
        User user  
) {  

    return new UserProfileDto(  
            user.getId(),  
            user.getNickname(),  
            user.getEmail(),  
            user.getRole(),  
            user.getVerified(),  
            user.getPhoneNumber(),  
            user.getCounty(),  
            user.getConstituency(),  
            user.getWard(),  
            user.getBio(),  
            user.getProfilePhoto(),  
            user.getCoverPhoto()  
    );  
}

}