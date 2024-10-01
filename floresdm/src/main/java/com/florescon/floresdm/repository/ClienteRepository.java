package com.florescon.floresdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.florescon.floresdm.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
