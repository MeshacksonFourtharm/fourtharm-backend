package com.fourtharm.backend.repository;

import com.fourtharm.backend.model.Constituency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConstituencyRepository
        extends JpaRepository<Constituency, Long> {

    List<Constituency> findByCounty_Id(
            Long countyId
    );

    List<Constituency>
    findByPinnedTrueOrderByPinOrderAsc();
}