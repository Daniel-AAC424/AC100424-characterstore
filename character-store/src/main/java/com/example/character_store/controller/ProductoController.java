package com.example.character_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.character_store.model.Producto;
import com.example.character_store.service.ProductoService;

@Controller
public class ProductoController {
	
    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping({"/", "/productos"})
    public String catalogo(
            @RequestParam(required = false) String universo,
            @RequestParam(required = false) String q,
            Model model) {
        model.addAttribute("productos", service.listar(universo, q));
        model.addAttribute("universo", universo);
        model.addAttribute("q", q);
        return "productos";
    }

    @GetMapping("/productos/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        Producto producto = service.buscar(id);
        if (producto == null) return "redirect:/productos";
        model.addAttribute("producto", producto);
        return "producto-detalle";
    }


}
