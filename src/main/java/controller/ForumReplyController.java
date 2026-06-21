package com.fourtharm.backend.controller;

import com.fourtharm.backend.dto.ReplyRequest;
import com.fourtharm.backend.model.ForumPost;
import com.fourtharm.backend.model.ForumReply;
import com.fourtharm.backend.model.User;
import com.fourtharm.backend.repository.ForumPostRepository;
import com.fourtharm.backend.repository.ForumReplyRepository;
import com.fourtharm.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin("*")
public class ForumReplyController {

    private final ForumReplyRepository replyRepository;
    private final ForumPostRepository postRepository;
    private final UserRepository userRepository;

    public ForumReplyController(
            ForumReplyRepository replyRepository,
            ForumPostRepository postRepository,
            UserRepository userRepository
    ) {
        this.replyRepository = replyRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ForumReply createComment(
            @RequestBody ReplyRequest request
    ) {

        ForumPost post =
                postRepository.findById(
                        request.getPostId()
                ).orElseThrow();

        User user =
                userRepository.findById(
                        request.getUserId()
                ).orElseThrow();

        ForumReply reply =
                new ForumReply();

        reply.setUserId(user.getId());
        reply.setNickname(user.getNickname());
        reply.setProfilePhoto(user.getProfilePhoto());
        reply.setVerified(user.getVerified());
        reply.setRole(user.getRole());
        reply.setContent(request.getContent());
        reply.setForumPost(post);

        if (request.getParentReplyId() != null) {

            ForumReply parent =
                    replyRepository.findById(
                            request.getParentReplyId()
                    ).orElseThrow();

            reply.setParentReply(parent);
        }

        ForumReply saved =
                replyRepository.save(reply);

        long count =
                replyRepository.countByForumPostId(
                        post.getId()
                );

        post.setRepliesCount(count);

        postRepository.save(post);

        return saved;
    }

    @GetMapping("/{postId}")
public List<ForumReply> getComments(
        @PathVariable Long postId
) {

    List<ForumReply> parents =
            replyRepository
                    .findByForumPostIdAndParentReplyIsNullOrderByCreatedAtAsc(
                            postId
                    );

    parents.forEach(parent -> {

        parent.setChildReplies(
                replyRepository
                        .findByParentReplyIdOrderByCreatedAtAsc(
                                parent.getId()
                        )
        );
    });

    return parents;
}

    @GetMapping("/children/{parentReplyId}")
    public List<ForumReply> getChildComments(
            @PathVariable Long parentReplyId
    ) {

        return replyRepository
                .findByParentReplyIdOrderByCreatedAtAsc(
                        parentReplyId
                );
    }
}