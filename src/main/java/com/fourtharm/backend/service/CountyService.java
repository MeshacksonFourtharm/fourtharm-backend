package com.fourtharm.backend.service;

import com.fourtharm.backend.model.County;
import com.fourtharm.backend.repository.CountyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountyService {

    private final CountyRepository countyRepository;

    public CountyService(CountyRepository countyRepository) {
        this.countyRepository = countyRepository;
    }

    public List<County> getAllCounties() {
        return countyRepository.findAll();
    }
}