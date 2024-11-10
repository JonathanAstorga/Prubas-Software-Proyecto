/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Objetos;

import DTOs.ContactoDTO;
import DTOs.PersonaDTO;
import Objetos.Interfaces.IObjetoNegocioPersona;
import entidades.Contacto;
import entidades.Persona;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author jl4ma
 */
public class ObjetoNegocioPersonaIT {
    
    @Mock
    private IObjetoNegocioPersona negocio;

    @InjectMocks
    private ObjetoNegocioPersona objetoNegocioPersona;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConvertirDTOAEntidad_PersonaDTO() {
        // arrange
        PersonaDTO persona = new PersonaDTO("123");
        Persona expected = new Persona("123");
        when(negocio.convertirDTOAEntidad(persona)).thenReturn(expected);

        // act
        Persona result = objetoNegocioPersona.convertirDTOAEntidad(persona);

        // assert
        assertEquals("123", result.getCurp());
    }

    @Test
    public void testConvertirEntidadADTOCURP() {
        // arrange
        Persona persona = new Persona("123");
        PersonaDTO expected = new PersonaDTO("123");
        when(negocio.convertirEntidadADTOCURP(persona)).thenReturn(expected);

        // act
        PersonaDTO result = objetoNegocioPersona.convertirEntidadADTOCURP(persona);

        // assert
        assertEquals("123", result.getCurp());
    }

    @Test
    public void testConvertirEntidadADTO_Persona() {
        // arrange
        Persona persona = new Persona("123");
        PersonaDTO expected = new PersonaDTO("123");
        when(negocio.convertirEntidadADTO(persona)).thenReturn(expected);

        // act
        PersonaDTO result = objetoNegocioPersona.convertirEntidadADTO(persona);

        // assert
        assertEquals("123", result.getCurp());
    }

    @Test
    public void testRegistrar() {
        // arrange
        PersonaDTO persona = new PersonaDTO("123");
        when(negocio.registrar(persona)).thenReturn(true);

        // act
        boolean result = objetoNegocioPersona.registrar(persona);

        // assert
        assertTrue(result);
    }

    @Test
    public void testPersonaRegistrada() {
        // arrange
        PersonaDTO persona = new PersonaDTO("123");
        when(negocio.personaRegistrada(persona)).thenReturn(true);

        // act
        boolean result = objetoNegocioPersona.personaRegistrada(persona);

        // assert
        assertTrue(result);
    }

    @Test
    public void testObtenerPersonaDTOPorCurp() {
        // arrange
        PersonaDTO persona = new PersonaDTO("123");
        when(negocio.obtenerPersonaDTOPorCurp(persona)).thenReturn(persona);

        // act
        PersonaDTO result = objetoNegocioPersona.obtenerPersonaDTOPorCurp(persona);

        // assert
        assertEquals("123", result.getCurp());
    }

    @Test
    public void testProcesarInicioSesion() {
        // arrange
        String contra = "123";
        String user = "madero";
        when(negocio.procesarInicioSesion(user, contra)).thenReturn(true);

        // act
        boolean result = objetoNegocioPersona.procesarInicioSesion(user, contra);

        // assert
        assertTrue(result);
    }

    @Test
    public void testObtenerPersonaPorTelefonoYContrasena() {
        // arrange
        String contra = "123";
        String telefono = "6441";
        PersonaDTO expected = new PersonaDTO("6441");
        when(negocio.obtenerPersonaPorTelefonoYContrasena(telefono, contra)).thenReturn(expected);

        // act
        PersonaDTO result = objetoNegocioPersona.obtenerPersonaPorTelefonoYContrasena(telefono, contra);

        // assert
        assertEquals("6441", result.getTelefono());
    }

    

    @Test
    public void testInsertMasivo() {
        // arrange
        List<PersonaDTO> personas = new ArrayList<>();
        personas.add(new PersonaDTO("123"));
        personas.add(new PersonaDTO("456"));
        personas.add(new PersonaDTO("789"));
        when(negocio.insertMasivo()).thenReturn(true);

        // act
        boolean result = objetoNegocioPersona.insertMasivo();

        // assert
        assertTrue(result);
    }
    
}
