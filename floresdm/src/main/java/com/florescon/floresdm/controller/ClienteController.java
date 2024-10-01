package com.florescon.floresdm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.florescon.floresdm.model.Cliente;
import com.florescon.floresdm.service.ClienteService;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.getAllClientes();
        model.addAttribute("clientes", clientes);
        return "cliente/list-cliente";
    }

    @GetMapping("/clientes/nuevo")
    public String mostrarFormularioCrearCliente(Model model) {
        System.out.println("INGRESA POR CLIENTES NUEVO");
        model.addAttribute("cliente", new Cliente());
        return "cliente/nuevo-cliente";
    }


    @GetMapping("/clientes/editar/{id}")
    public String mostrarFormularioEditarCliente(@PathVariable("id") Long id, Model model) {
        Cliente cliente = clienteService.getClienteById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        model.addAttribute("cliente", cliente);
        return "cliente/edit-cliente";
    }

    @PostMapping("/clientes/actualizar/{id}")
    public String actualizarCliente(@PathVariable("id") Long id, Cliente cliente) {
        clienteService.saveCliente(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(@PathVariable("id") Long id) {
        clienteService.deleteCliente(id);
        return "redirect:/clientes";
    }
}
