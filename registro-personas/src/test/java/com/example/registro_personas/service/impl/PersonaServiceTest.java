package com.example.registro_personas.service.impl;

import com.example.registro_personas.entity.Persona;
import com.example.registro_personas.repository.PersonaRepository;
import com.example.registro_personas.service.PersonaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonaServiceTest {

    @InjectMocks
    private PersonaService personaService;

    @Mock
    private PersonaRepository personaRepository;

    private Persona persona;

    @BeforeEach
    void setUp() {
        // Configuración inicial para la prueba
        persona = new Persona();
        persona.setId(1L);
        persona.setNombre("Juan");
        persona.setEdad(30);
        persona.setEmail("juan.perez@email.com");
    }

    // --- Prueba para guardarPersona() ---
    @Test
    void testGuardarPersona_Success() {
        when(personaRepository.save(persona)).thenReturn(persona);

        Persona resultado = personaService.guardarPersona(persona);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        verify(personaRepository, times(1)).save(persona);
    }

    @Test
    void testGuardarPersona_ThrowsException() {
        when(personaRepository.save(persona)).thenThrow(new RuntimeException("Error de BD"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            personaService.guardarPersona(persona);
        });

        assertTrue(ex.getMessage().contains("Error al guardar la persona"));
        verify(personaRepository, times(1)).save(persona);
    }

    // --- Prueba para listarPersonas() ---
    @Test
    void testListarPersonas_Success() {
        List<Persona> lista = new ArrayList<>();
        lista.add(persona);
        lista.add(new Persona(2L, "Ana", 25, "ana@email.com"));
        when(personaRepository.findAll()).thenReturn(lista);

        List<Persona> resultado = personaService.listarPersonas();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(personaRepository, times(1)).findAll();
    }

    @Test
    void testListarPersonas_ThrowsException() {
        when(personaRepository.findAll()).thenThrow(new RuntimeException("Fallo de conexión"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            personaService.listarPersonas();
        });

        assertTrue(ex.getMessage().contains("Error al listar las personas"));
        verify(personaRepository, times(1)).findAll();
    }
}















































