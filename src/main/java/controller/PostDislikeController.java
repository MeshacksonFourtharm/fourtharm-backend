package com.fourtharm.backend.controller;

import com.fourtharm.backend.dto.DislikeRequest;
import com.fourtharm.backend.model.ForumPost;
import com.fourtharm.backend.model.PostDislike;
import com.fourtharm.backend.repository.ForumPostRepository;
import com.fourtharm.backend.repository.PostDislikeRepository;
import com.fourtharm.backend.repository.PostLikeRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dislikes")
@CrossOrigin("*")
public class PostDislikeController {

    private final PostDislikeRepository dislikeRepository;
    private final PostLikeRepository likeRepository;
    private final ForumPostRepository postRepository;

    public PostDislikeController(
            PostDislikeRepository dislikeRepository,
            PostLikeRepository likeRepository,
            ForumPostRepository postRepository
    ) {
        this.dislikeRepository = dislikeRepository;
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }

    @PostMapping
    public Long toggleDislike(
            @RequestBody DislikeRequest request
    ) {

        Long postId = request.getPostId();
        Long userId = request.getUserId();

        ForumPost post =
                postRepository.findById(postId)
                        .orElseThrow();

        if (
                dislikeRepository.existsByForumPostIdAndUserId(
                        postId,
                        userId
                )
        ) {

            dislikeRepository.deleteByForumPostIdAndUserId(
                    postId,
                    userId
            );

        } else {

            likeRepository.deleteByForumPostIdAndUserId(
                    postId,
                    userId
            );

            dislikeRepository.save(
                    new PostDislike(
                            userId,
                            post
                    )
            );
        }

        long likes =
                likeRepository.countByForumPostId(
                        postId
                );

        long dislikes =
                dislikeRepository.countByForumPostId(
                        postId
                );

        post.setLikesCount(likes);
        post.setDislikesCount(dislikes);

        postRepository.save(post);

        return dislikes;
    }

    @GetMapping("/{postId}")
    public Long getDislikes(
            @PathVariable Long postId
    ) {

        return dislikeRepository.countByForumPostId(
                postId
        );
    }
}