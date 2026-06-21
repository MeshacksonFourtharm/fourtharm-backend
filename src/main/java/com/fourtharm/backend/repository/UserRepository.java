package com.fourtharm.backend.repository;

import com.fourtharm.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository
extends JpaRepository<User, Long> {

    User findByEmail(
            String email
    );

    boolean existsByEmail(
            String email
    );

    boolean existsByNationalId(
            String nationalId
    );

    List<User> findByRole(
            String role
    );
}