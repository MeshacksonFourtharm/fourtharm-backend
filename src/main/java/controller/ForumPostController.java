package com.fourtharm.backend.controller;

import com.fourtharm.backend.model.ForumPost;
import com.fourtharm.backend.model.User;
import com.fourtharm.backend.repository.ForumPostRepository;
import com.fourtharm.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forum")
@CrossOrigin("*")
public class ForumPostController {

private final ForumPostRepository repository;

private final UserRepository userRepository;

public ForumPostController(
        ForumPostRepository repository,
        UserRepository userRepository
) {
    this.repository = repository;
    this.userRepository = userRepository;
}

@PostMapping("/posts")
public ForumPost createPost(
        @RequestBody ForumPost post
) {

    User user =
            userRepository.findById(
                    post.getUserId()
            ).orElseThrow();

    boolean allowed = false;

    switch (
            post.getGovernanceLevel()
                    .toUpperCase()
    ) {

        case "NATIONAL":
            allowed = true;
            break;

        case "COUNTY":

            allowed =
                    user.getCounty() != null &&
                    user.getCounty().equals(
                            post.getCounty()
                    );
            break;

        case "CONSTITUENCY":

            allowed =
                    user.getConstituency() != null &&
                    user.getConstituency().equals(
                            post.getConstituency()
                    ) &&
                    user.getCounty() != null &&
                    user.getCounty().equals(
                            post.getCounty()
                    );
            break;

        case "WARD":

            allowed =
                    user.getWard() != null &&
                    user.getWard().equals(
                            post.getWard()
                    );
            break;
    }

    if (!allowed) {

        throw new RuntimeException(
                "You cannot post outside your jurisdiction"
        );
    }

    post.setNickname(
            user.getNickname()
    );

    post.setProfilePhoto(
            user.getProfilePhoto()
    );

    post.setVerified(
            user.getVerified()
    );

    post.setRole(
            user.getRole()
    );

    return repository.save(
            post
    );
}

@GetMapping("/posts")
public List<ForumPost> getPosts(

        @RequestParam String level,

        @RequestParam(required = false)
        String county,

        @RequestParam(required = false)
        String constituency,

        @RequestParam(required = false)
        String ward
) {

    List<ForumPost> posts;

    switch (
            level.toUpperCase()
    ) {

        case "NATIONAL":

            posts =
                    repository.findByGovernanceLevel(
                            level
                    );
            break;

        case "COUNTY":

            posts =
                    repository.findByGovernanceLevelAndCounty(
                            level,
                            county
                    );
            break;

        case "CONSTITUENCY":

            posts =
                    repository
                            .findByGovernanceLevelAndCountyAndConstituency(
                                    level,
                                    county,
                                    constituency
                            );
            break;

        case "WARD":

            posts =
                    repository
                            .findByGovernanceLevelAndCountyAndConstituencyAndWard(
                                    level,
                                    county,
                                    constituency,
                                    ward
                            );
            break;

        default:

            return List.of();
    }

    for (ForumPost post : posts) {

        userRepository
                .findById(
                        post.getUserId()
                )
                .ifPresent(user -> {

                    post.setProfilePhoto(
                            user.getProfilePhoto()
                    );

                    post.setNickname(
                            user.getNickname()
                    );

                    post.setVerified(
                            user.getVerified()
                    );

                    post.setRole(
                            user.getRole()
                    );
                });
    }

    return posts;
}

}