package com.example.registro_personas.service;

import com.example.registro_personas.entity.Persona;
import com.example.registro_personas.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public Persona guardarPersona(Persona persona) {
        try {
            return personaRepository.save(persona);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la persona", e);  // Lanzamos una excepción personalizada
        }
    }

    public List<Persona> listarPersonas() {
        try {
            return personaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar las personas", e);  // Lanzamos una excepción personalizada
        }
    }
}


