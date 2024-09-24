package com.florescon.floresdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.florescon.floresdm.model.Flor;

public interface FlorRepository extends JpaRepository<Flor, Long> {
}
