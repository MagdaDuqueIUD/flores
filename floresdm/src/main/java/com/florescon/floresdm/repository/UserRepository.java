package com.florescon.floresdm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.florescon.floresdm.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

