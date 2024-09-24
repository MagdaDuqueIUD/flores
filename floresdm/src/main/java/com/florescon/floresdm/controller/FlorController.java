package com.florescon.floresdm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        return "flor/create-flor";
    }

    @PostMapping("/flores/guardar")
    public String guardarFlor(Flor flor) {
        florService.saveFlor(flor);
        return "redirect:/flores";
    }
}
