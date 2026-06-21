package com.fourtharm.backend.repository;

import com.fourtharm.backend.model.UserPinnedJurisdiction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPinnedJurisdictionRepository
        extends JpaRepository<UserPinnedJurisdiction, Long> {

    List<UserPinnedJurisdiction>
    findByUserIdAndJurisdictionTypeOrderByPinOrderAsc(
            Long userId,
            String jurisdictionType
    );

    long countByUserIdAndJurisdictionType(
            Long userId,
            String jurisdictionType
    );

    UserPinnedJurisdiction
    findByUserIdAndJurisdictionTypeAndJurisdictionId(
            Long userId,
            String jurisdictionType,
            Long jurisdictionId
    );
}