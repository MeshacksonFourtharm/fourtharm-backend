package com.fourtharm.backend.repository;

import com.fourtharm.backend.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WardRepository
        extends JpaRepository<Ward, Long> {

    List<Ward> findByConstituency_Id(
            Long constituencyId
    );

    List<Ward>
    findByPinnedTrueOrderByPinOrderAsc();
}