package com.fourtharm.backend.repository;

import com.fourtharm.backend.model.County;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountyRepository
        extends JpaRepository<County, Long> {

    List<County> findByPinnedTrueOrderByPinOrderAsc();
}