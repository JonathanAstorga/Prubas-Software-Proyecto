/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Objetos;

import DTOs.ContactoDTO;
import DTOs.PersonaDTO;
import Objetos.Interfaces.IObjetoNegocioContacto;
import entidades.Contacto;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author jl4ma
 */
public class ObjetoNegocioContactoIT {
    
    @Mock
    private IObjetoNegocioContacto negocio;

    @InjectMocks
    private ObjetoNegocioContactoIT objetoNegocioContactoIT;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConvertirDTOAEntidad() {
        // Arrange
        ContactoDTO contactoDTO = new ContactoDTO("jose");
        Contacto contacto = new Contacto("jose");
        when(negocio.convertirDTOAEntidad(contactoDTO)).thenReturn(contacto);

        // Act
        Contacto result = negocio.convertirDTOAEntidad(contactoDTO);

        // Assert
        assertEquals("jose", result.getAlias());
        verify(negocio).convertirDTOAEntidad(contactoDTO);
    }

    @Test
    public void testConvertirEntidadADTO() {
        // Arrange
        Contacto contacto = new Contacto("jose");
        ContactoDTO contactoDTO = new ContactoDTO("jose");
        when(negocio.convertirEntidadADTO(contacto)).thenReturn(contactoDTO);

        // Act
        ContactoDTO result = negocio.convertirEntidadADTO(contacto);

        // Assert
        assertEquals("jose", result.getAlias());
        verify(negocio).convertirEntidadADTO(contacto);
    }

    @Test
    public void testAgregar() {
        // Arrange
        PersonaDTO persona = new PersonaDTO("123");
        ContactoDTO contacto = new ContactoDTO("jose");
        when(negocio.agregar(persona, contacto)).thenReturn(true);

        // Act
        Boolean result = negocio.agregar(persona, contacto);

        // Assert
        assertTrue(result);
        verify(negocio).agregar(persona, contacto);
    }

    @Test
    public void testEliminar() {
        // Arrange
        PersonaDTO persona = new PersonaDTO("123");
        ContactoDTO contacto = new ContactoDTO("jose");
        when(negocio.eliminar(persona, contacto)).thenReturn(true);

        // Act
        Boolean result = negocio.eliminar(persona, contacto);

        // Assert
        assertTrue(result);
        verify(negocio).eliminar(persona, contacto);
    }

    @Test
    public void testActualizar() {
        // Arrange
        PersonaDTO persona = new PersonaDTO("123");
        ContactoDTO contacto = new ContactoDTO("jose");
        ContactoDTO contacto2 = new ContactoDTO("Luis");
        when(negocio.actualizar(persona, contacto, contacto2)).thenReturn(true);

        // Act
        Boolean result = negocio.actualizar(persona, contacto, contacto2);

        // Assert
        assertTrue(result);
        verify(negocio).actualizar(persona, contacto, contacto2);
    }

    @Test
    public void testObtenerContactosDTOPersona() {
        // Arrange
        PersonaDTO persona = new PersonaDTO("123");
        List<ContactoDTO> prueba = new ArrayList<>();
        prueba.add(new ContactoDTO("jose"));
        when(negocio.obtenerContactosDTOPersona(persona)).thenReturn(prueba);

        // Act
        List<ContactoDTO> result = negocio.obtenerContactosDTOPersona(persona);

        // Assert
        assertEquals(prueba.get(0).getAlias(), result.get(0).getAlias());
        verify(negocio).obtenerContactosDTOPersona(persona);
    }

    @Test
    public void testObtenerContactoDTOPersona() {
        // Arrange
        PersonaDTO persona = new PersonaDTO("123");
        ContactoDTO contacto = new ContactoDTO("jose");
        when(negocio.obtenerContactoDTOPersona(persona, contacto)).thenReturn(contacto);

        // Act
        ContactoDTO result = negocio.obtenerContactoDTOPersona(persona, contacto);

        // Assert
        assertEquals("jose", result.getAlias());
        verify(negocio).obtenerContactoDTOPersona(persona, contacto);
    }
    
}
