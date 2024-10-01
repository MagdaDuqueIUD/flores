package com.florescon.floresdm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.florescon.floresdm.model.Flor;
import com.florescon.floresdm.service.FlorService;

@Controller
public class FlorController {

    @Autowired
    private FlorService florService;

    @GetMapping("/flores")
    public String listarFlores(Model model) {
        model.addAttribute("flores", florService.getAllFlores());
        return "flor/list-flor";
    }

    @GetMapping("/flores/nuevo")
    public String mostrarFormularioCrearFlor(Model model) {
        model.addAttribute("flor", new Flor());
        return "flor/nueva-flor";
    }

    @GetMapping("/flores/editar/{id}")
    public String mostrarFormularioEditarFlor(@PathVariable("id") Long id, Model model) {
        Flor flor = florService.getFlorById(id).orElseThrow(() -> new IllegalArgumentException("Flor no encontrada"));
        model.addAttribute("flor", flor);
        return "flor/edit-flor";  // Nombre del template para editar la flor
    }

    @PostMapping("/flores/guardar")
    public String guardarFlor(Flor flor) {
        florService.saveFlor(flor);
        return "redirect:/flores";
    }

    @PostMapping("/flores/actualizar/{id}")
    public String actualizarFlor(@PathVariable("id") Long id, @ModelAttribute Flor flor) {
        // Verificamos que el ID está asignado correctamente al objeto Flor
        flor.setId(id);
        // Guarda la flor actualizada
        florService.saveFlor(flor);
        // Redirige de nuevo a la lista de flores
        return "redirect:/flores";
    }

    // Eliminar una flor
    @GetMapping("/flores/borrar/{id}")
    public String borrarFlor(@PathVariable("id") Long id) {
        florService.deleteFlorById(id);
        return "redirect:/flores";
    }

}




















