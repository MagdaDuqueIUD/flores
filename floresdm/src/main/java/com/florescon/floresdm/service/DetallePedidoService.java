package com.florescon.floresdm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.florescon.floresdm.model.DetallePedido;
import com.florescon.floresdm.repository.DetallePedidoRepository;

@Service
public class DetallePedidoService {
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public DetallePedido saveDetallePedido(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    public void deleteDetallePedido(Long id) {
        detallePedidoRepository.deleteById(id);
    }

    public int getFlor(Long id) {
        return 10;
    }
    
    public int getCantidadSolicitada(Long id) {
        return 10;
    }

}

