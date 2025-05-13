package com.example.registro_personas.controller;

import com.example.registro_personas.entity.Persona;
import com.example.registro_personas.service.PersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping
    public ResponseEntity<?> registrarPersona(@Valid @RequestBody Persona persona) {
        try {
            Persona guardada = personaService.guardarPersona(persona);
            return ResponseEntity.ok(guardada);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la persona", e);  // Lanza una excepción en caso de error
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerPersonas() {
        try {
            List<Persona> personas = personaService.listarPersonas();
            return ResponseEntity.ok(personas);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las personas", e);  // Lanza una excepción en caso de error
        }
    }
}


