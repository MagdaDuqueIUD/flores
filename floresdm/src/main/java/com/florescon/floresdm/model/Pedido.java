package com.florescon.floresdm.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clienteNombre;
    private String clienteDireccion;
    private String clienteContacto;
    private String tipoArreglo;
    private String ocasion;
    private LocalDate fechaEntrega;
    private double presupuesto;
    private String estado;
}
