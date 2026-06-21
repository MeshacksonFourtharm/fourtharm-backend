package com.fourtharm.backend.repository;

import com.fourtharm.backend.model.ForumReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumReplyRepository
extends JpaRepository<ForumReply, Long> {

List<ForumReply>
findByForumPostIdAndParentReplyIsNullOrderByCreatedAtAsc(
        Long postId
);

List<ForumReply>
findByParentReplyIdOrderByCreatedAtAsc(
        Long parentReplyId
);

long countByForumPostId(
        Long postId
);

}