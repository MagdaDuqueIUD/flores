package com.florescon.floresdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.florescon.floresdm.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}

