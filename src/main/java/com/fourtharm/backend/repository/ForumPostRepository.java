package com.fourtharm.backend.repository;

import com.fourtharm.backend.model.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumPostRepository
        extends JpaRepository<ForumPost, Long> {

    List<ForumPost> findByGovernanceLevel(
            String governanceLevel
    );

    List<ForumPost> findByGovernanceLevelAndCounty(
            String governanceLevel,
            String county
    );

    List<ForumPost> findByGovernanceLevelAndCountyAndConstituency(
            String governanceLevel,
            String county,
            String constituency
    );

    List<ForumPost> findByGovernanceLevelAndCountyAndConstituencyAndWard(
            String governanceLevel,
            String county,
            String constituency,
            String ward
    );

    List<ForumPost> findByPinnedTrue();

    long countByPinnedTrue();
}