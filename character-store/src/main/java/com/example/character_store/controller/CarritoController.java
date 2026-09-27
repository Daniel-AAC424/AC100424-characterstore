package com.example.character_store.controller;

import java.math.BigDecimal;
import java.util.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.character_store.model.Producto;
import com.example.character_store.service.ProductoService;

@Controller
@RequestMapping("/carrito")
public class CarritoController {
	
    private final ProductoService productoService;

    public CarritoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @SuppressWarnings("unchecked")
    private Map<Long,Integer> carrito(HttpSession session) {
        Map<Long,Integer> carrito =
            (Map<Long,Integer>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new LinkedHashMap<>();
            session.setAttribute("carrito", carrito);
        }
        return carrito;
    }

    @PostMapping("/agregar/{id}")
    public String agregar(@PathVariable Long id, HttpSession session) {
        Producto p = productoService.buscar(id);
        if (p != null && p.getStock() != null && p.getStock() > 0) {
            carrito(session).merge(id, 1, Integer::sum);
        }
        return "redirect:/productos/" + id;
    }

    @GetMapping
    public String ver(HttpSession session, Model model) {
        List<ItemCarritoView> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (var entry : carrito(session).entrySet()) {
            Producto p = productoService.buscar(entry.getKey());
            if (p == null) continue;
            BigDecimal subtotal = p.getPrecio()
                .multiply(BigDecimal.valueOf(entry.getValue()));
            total = total.add(subtotal);
            items.add(new ItemCarritoView(p, entry.getValue(), subtotal));
        }

        model.addAttribute("items", items);
        model.addAttribute("total", total);
        return "carrito";
    }

    public record ItemCarritoView(
        Producto producto, Integer cantidad, BigDecimal subtotal) {}


}
