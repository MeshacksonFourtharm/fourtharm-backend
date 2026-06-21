package com.fourtharm.backend.repository;

import com.fourtharm.backend.model.PostDislike;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface PostDislikeRepository
        extends JpaRepository<PostDislike, Long> {

    long countByForumPostId(
            Long postId
    );

    boolean existsByForumPostIdAndUserId(
            Long postId,
            Long userId
    );

    @Transactional
    @Modifying
    void deleteByForumPostIdAndUserId(
            Long postId,
            Long userId
    );
}