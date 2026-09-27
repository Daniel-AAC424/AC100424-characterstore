package com.example.character_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.character_store.service.SimpsonsMigrationService;
import com.example.character_store.service.RickMortyMigrationService;



@Controller
@RequestMapping("/admin/migraciones")
public class MigrationController {

    private final SimpsonsMigrationService simpsonsMigrationService;
    private final RickMortyMigrationService rickMortyMigrationService;


    public MigrationController(
            SimpsonsMigrationService simpsonsMigrationService,
            RickMortyMigrationService rickMortyMigrationService) {
        this.simpsonsMigrationService = simpsonsMigrationService;
        this.rickMortyMigrationService = rickMortyMigrationService;

    }

    @GetMapping
    public String pantalla() {
        return "migraciones";
    }

    @PostMapping("/simpsons")
    public String migrarSimpsons(Model model) {
        int insertados = simpsonsMigrationService.migrarTodos();
        model.addAttribute("mensaje",
            "Migración terminada. Nuevos registros: " + insertados);
        return "migraciones";
    }
    
    @PostMapping("/rick-and-morty")
    public String migrarRickMorty(Model model) {
        int insertados = rickMortyMigrationService.migrarTodos();
        model.addAttribute("mensaje",
            "Migración Rick and Morty terminada. Nuevos registros: " + insertados);
        return "migraciones";
    }
    

	
}
