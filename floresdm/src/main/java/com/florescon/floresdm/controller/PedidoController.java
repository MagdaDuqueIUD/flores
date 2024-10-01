package com.florescon.floresdm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.florescon.floresdm.model.Cliente;
import com.florescon.floresdm.model.DetallePedido;
import com.florescon.floresdm.model.Flor;
import com.florescon.floresdm.model.Pedido;
import com.florescon.floresdm.service.ClienteService;
import com.florescon.floresdm.service.FlorService;
import com.florescon.floresdm.service.PedidoService;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FlorService florService;

    /*@GetMapping
    public String listarPedidos(Model model) {
        List<Pedido> pedidos = pedidoService.getAllPedidos();
        model.addAttribute("pedidos", pedidos);
        return "pedido/list-pedido";
    }*/

    @GetMapping
    public String listarPedidos(Model model) {
        model.addAttribute("pedidos", pedidoService.findAllPedidos());
        return "pedido/list-pedido";
    }  
    
    @GetMapping("/nuevo")
    public String mostrarFormularioCrearPedido(Model model) {
        List<Cliente> clientes = clienteService.getAllClientes();
        List<Flor> flores = florService.getAllFlores();
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("clientes", clientes);
        model.addAttribute("flores", flores);
        return "pedido/nuevo-pedido";
    }

    @GetMapping("/pedidos/nuevoA")
    public String crearPedido(Model model) {
        System.out.println("RUTA NUEVO PEDIDOA");
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("clientes", clienteService.getAllClientes());
        model.addAttribute("flores", florService.getAllFlores());
        System.out.println("RUTA NUEVO PEDIDO");
        return "pedido/nuevo-pedido";
    }


    /*@PostMapping("/guardar")
    public String guardarPedido(Pedido pedido) {
        pedidoService.savePedido(pedido);
        return "redirect:/pedidos";
    }*/

    /*@PostMapping("/guardar")
    public String guardarPedido(@ModelAttribute Pedido pedido, Model model) {
        System.out.println("INGRESÓ A GUARDAR PEDIDO");
        try {
            pedidoService.savePedido(pedido);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "pedido/list-pedido";
        }
        return "redirect:/pedidos";
    }*/


    @PostMapping("/guardar")
    public String guardarPedido(@ModelAttribute Pedido pedido, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("flores", florService.getAllFlores());
            model.addAttribute("clientes", clienteService.getAllClientes());
            return "pedido/formulario";
        }
    
        // Asignar el pedido a cada detalle del pedido
        for (DetallePedido detalle : pedido.getDetalles()) {
            detalle.setPedido(pedido); // Esto asegura que cada detalle tiene referencia al pedido
        }
    
        // Guardar el pedido y los detalles
        pedidoService.savePedido(pedido);
        return "redirect:/pedidos";
    }


    @PostMapping("/guardarzz")
    public String guardarPedidozz(@ModelAttribute("pedido") Pedido pedido) {
        // Validar la cantidad disponible de flores antes de guardar el pedido
        for (DetallePedido detalle : pedido.getDetalles()) {
            
            //Flor flor = florService.obtenerFlorPorId(detalle.getFlor().getId());

            //Flor flor = florService.obtenerFlorPorId(detalle.getFlor().getId());
            
            /*if (flor.getCantidadDisponible() < detalle.getCantidad()) {
                // Mostrar un mensaje de error si no hay suficiente cantidad
                return "redirect:/pedidos/nuevo?error=CantidadNoDisponible";
            }*/
        }
        // Guardar el pedido y sus detalles
        pedidoService.savePedido(pedido);// guardarPedido(pedido);
        return "redirect:/pedidos";
    }


@PostMapping("/guardarqq")
public String guardarPedidoqq(@ModelAttribute Pedido pedido, BindingResult result, Model model) {
    if (result.hasErrors()) {
        model.addAttribute("flores", florService.getAllFlores());
        model.addAttribute("clientes", clienteService.getAllClientes());
        return "pedido/formulario";
    }

    for (DetallePedido detalle : pedido.getDetalles()) {
        // Obtener la flor por ID usando Optional
        //Flor optionalFlor = florService.getFlorById(detalle.getFlor().getId());

        /*if (optionalFlor.isPresent()) {
            Flor flor = optionalFlor.get();

            // Validar si hay suficiente cantidad disponible
            if (flor.getCantidadDisponible() < detalle.getCantidadSolicitada()) {
                // Manejar el error si no hay suficiente cantidad
                result.rejectValue("detalles", "error.detalles", "No hay suficiente cantidad de la flor seleccionada.");
                model.addAttribute("flores", florService.getAllFlores());
                model.addAttribute("clientes", clienteService.getAllClientes());
                return "pedido/formulario";
            }

            // Setear el precio y el subtotal
            detalle.setPrecioFlor(flor.getPrecioVenta());
            detalle.setSubtotal(detalle.getCantidadSolicitada() * flor.getPrecioVenta());
        } else {
            // Manejar el caso en que no se encuentra la flor
            result.rejectValue("detalles", "error.detalles", "Flor no encontrada.");
            model.addAttribute("flores", florService.getAllFlores());
            model.addAttribute("clientes", clienteService.getAllClientes());
            return "pedido/formulario";
        }*/
    }

    // Guardar el pedido y los detalles
    pedidoService.savePedido(pedido);
    return "redirect:/pedidos";
}




    @GetMapping("/editar/{id}")
    public String editarPedido(@PathVariable Long id, Model model) {
        Pedido pedido = pedidoService.findPedidoById(id);
        model.addAttribute("pedido", pedido);
        model.addAttribute("clientes", clienteService.getAllClientes());
        model.addAttribute("flores", florService.getAllFlores());
        return "pedido/list-pedido";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPedido(@PathVariable Long id) {
        pedidoService.deletePedido(id);
        return "redirect:/pedidos";
    }


    @GetMapping("/detalle/{id}")
    public String verDetallesPedido(@PathVariable Long id, Model model) {
        Pedido pedido = pedidoService.findPedidoById(id);
        model.addAttribute("pedido", pedido);
        return "pedido/detalle-pedido";
    }

    
}

