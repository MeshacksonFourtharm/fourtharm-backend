package com.fourtharm.backend.service;

import com.fourtharm.backend.model.UserPinnedJurisdiction;
import com.fourtharm.backend.repository.UserPinnedJurisdictionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPinnedJurisdictionService {

    private final UserPinnedJurisdictionRepository repository;

    public UserPinnedJurisdictionService(
            UserPinnedJurisdictionRepository repository
    ) {
        this.repository = repository;
    }

    public List<UserPinnedJurisdiction> getPins(
            Long userId,
            String jurisdictionType
    ) {

        return repository
                .findByUserIdAndJurisdictionTypeOrderByPinOrderAsc(
                        userId,
                        jurisdictionType
                );
    }

    public UserPinnedJurisdiction pinJurisdiction(
            Long userId,
            String jurisdictionType,
            Long jurisdictionId
    ) {

        UserPinnedJurisdiction existing =
                repository.findByUserIdAndJurisdictionTypeAndJurisdictionId(
                        userId,
                        jurisdictionType,
                        jurisdictionId
                );

        if (existing != null) {
            return existing;
        }

        long count =
                repository.countByUserIdAndJurisdictionType(
                        userId,
                        jurisdictionType
                );

        if (count >= 2) {

            throw new RuntimeException(
                    "Maximum of 2 pinned "
                            + jurisdictionType.toLowerCase()
                            + " forums allowed"
            );
        }

        UserPinnedJurisdiction pin =
                new UserPinnedJurisdiction();

        pin.setUserId(userId);
        pin.setJurisdictionType(jurisdictionType);
        pin.setJurisdictionId(jurisdictionId);

        pin.setPinOrder((int) count + 1);

        return repository.save(pin);
    }

    public void unpinJurisdiction(
            Long userId,
            String jurisdictionType,
            Long jurisdictionId
    ) {

        UserPinnedJurisdiction existing =
                repository.findByUserIdAndJurisdictionTypeAndJurisdictionId(
                        userId,
                        jurisdictionType,
                        jurisdictionId
                );

        if (existing != null) {
            repository.delete(existing);
        }
    }
}