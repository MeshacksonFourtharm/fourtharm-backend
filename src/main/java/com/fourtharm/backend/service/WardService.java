package com.fourtharm.backend.service;

import com.fourtharm.backend.model.Ward;
import com.fourtharm.backend.repository.WardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardService {

private final WardRepository wardRepository;

public WardService(
        WardRepository wardRepository
) {
    this.wardRepository =
            wardRepository;
}

public List<Ward> getAllWards() {

    return wardRepository.findAll();
}

public List<Ward> getWardsByConstituency(
        Long constituencyId
) {

    return wardRepository.findByConstituency_Id(
            constituencyId
    );
}

}