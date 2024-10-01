package com.florescon.floresdm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String mostrarPaginaPrincipal() {
        return "index";  // Retorna la página principal index.html
    }
}
