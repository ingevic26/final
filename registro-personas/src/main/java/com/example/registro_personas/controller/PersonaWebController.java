package com.example.registro_personas.controller;

import com.example.registro_personas.entity.Persona;
import com.example.registro_personas.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/personas")
public class PersonaWebController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("persona", new Persona());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarPersona(@ModelAttribute Persona persona) {
        personaService.guardarPersona(persona);
        return "redirect:/personas/listado";
    }

    @GetMapping("/listado")
    public String mostrarListadoPersonas(Model model) {
        model.addAttribute("personas", personaService.listarPersonas());
        return "listado";
    }
}

