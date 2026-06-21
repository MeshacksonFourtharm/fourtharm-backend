package com.fourtharm.backend.controller;

import com.fourtharm.backend.dto.LikeRequest;
import com.fourtharm.backend.model.ForumPost;
import com.fourtharm.backend.model.PostLike;
import com.fourtharm.backend.repository.ForumPostRepository;
import com.fourtharm.backend.repository.PostDislikeRepository;
import com.fourtharm.backend.repository.PostLikeRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@CrossOrigin("*")
public class PostLikeController {

    private final PostLikeRepository likeRepository;
    private final PostDislikeRepository dislikeRepository;
    private final ForumPostRepository postRepository;

    public PostLikeController(
            PostLikeRepository likeRepository,
            PostDislikeRepository dislikeRepository,
            ForumPostRepository postRepository
    ) {
        this.likeRepository = likeRepository;
        this.dislikeRepository = dislikeRepository;
        this.postRepository = postRepository;
    }

    @PostMapping
    public Long toggleLike(
            @RequestBody LikeRequest request
    ) {

        Long postId = request.getPostId();
        Long userId = request.getUserId();

        ForumPost post =
                postRepository.findById(postId)
                        .orElseThrow();

        if (
                likeRepository.existsByForumPostIdAndUserId(
                        postId,
                        userId
                )
        ) {

            likeRepository.deleteByForumPostIdAndUserId(
                    postId,
                    userId
            );

        } else {

            dislikeRepository.deleteByForumPostIdAndUserId(
                    postId,
                    userId
            );

            likeRepository.save(
                    new PostLike(
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

        return likes;
    }

    @GetMapping("/{postId}")
    public Long getLikes(
            @PathVariable Long postId
    ) {

        return likeRepository.countByForumPostId(
                postId
        );
    }
}