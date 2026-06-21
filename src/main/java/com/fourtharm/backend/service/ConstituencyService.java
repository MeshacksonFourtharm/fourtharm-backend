package com.fourtharm.backend.service;

import com.fourtharm.backend.model.Constituency;
import com.fourtharm.backend.repository.ConstituencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConstituencyService {

private final ConstituencyRepository constituencyRepository;

public ConstituencyService(
        ConstituencyRepository constituencyRepository
) {
    this.constituencyRepository =
            constituencyRepository;
}

public List<Constituency> getAllConstituencies() {

    return constituencyRepository.findAll();
}

public List<Constituency> getConstituenciesByCounty(
        Long countyId
) {

    return constituencyRepository.findByCounty_Id(
            countyId
    );
}

}