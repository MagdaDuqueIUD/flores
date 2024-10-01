package com.florescon.floresdm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.florescon.floresdm.model.DetallePedido;
import com.florescon.floresdm.model.Flor;
import com.florescon.floresdm.model.Pedido;
import com.florescon.floresdm.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private FlorService florService;    

     
    @Transactional
    public Pedido savePedido(Pedido pedido) {
        for (DetallePedido detalle : pedido.getDetalles()) {
            Flor flor = detalle.getFlor();
        }
        System.out.println("PEDIDO SERVICE GUARDAR oca"+pedido.getOcasion()+"  pre "+pedido.getPresupuesto()+" tipo "+pedido.getTipoArreglo()+"  "+pedido.getId()+"  est "+pedido.getEstado());
        return pedidoRepository.save(pedido);
    }
        

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }
    public List<Pedido> findAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> getPedidoById(Long id) {
        return pedidoRepository.findById(id);
    }
    public Pedido findPedidoById(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }    



    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}








