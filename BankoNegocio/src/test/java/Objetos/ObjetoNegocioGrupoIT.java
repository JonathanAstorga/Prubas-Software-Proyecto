/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Objetos;

import DTOs.ContactoDTO;
import DTOs.GrupoDTO;
import Objetos.Interfaces.IObjetoNegocioGrupo;
import entidades.Contacto;
import entidades.Grupo;
import org.bson.types.ObjectId;
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
public class ObjetoNegocioGrupoIT {
    
    @Mock
    private IObjetoNegocioGrupo negocio;

    @InjectMocks
    private ObjetoNegocioGrupoIT objetoNegocioGrupoIT;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConvertirDTOAEntidad() {
        // Arrange
        GrupoDTO grupoDTO = new GrupoDTO("jose", 123.23, "pago");
        Grupo grupo = new Grupo("jose", 123.23, "pago");
        when(negocio.convertirDTOAEntidad(grupoDTO)).thenReturn(grupo);

        // Act
        Grupo result = negocio.convertirDTOAEntidad(grupoDTO);

        // Assert
        assertEquals("jose", result.getNombre());
        verify(negocio).convertirDTOAEntidad(grupoDTO);
    }

    @Test
    public void testConvertirEntidadADTO() {
        // Arrange
        Grupo grupo = new Grupo("jose", 123.23, "pago");
        GrupoDTO grupoDTO = new GrupoDTO("jose", 123.23, "pago");
        when(negocio.convertirEntidadADTO(grupo)).thenReturn(grupoDTO);

        // Act
        GrupoDTO result = negocio.convertirEntidadADTO(grupo);

        // Assert
        assertEquals("jose", result.getNombre());
        verify(negocio).convertirEntidadADTO(grupo);
    }

    @Test
    public void testCrearGrupo() {
        // Arrange
        GrupoDTO grupo = new GrupoDTO("jose", 123.23, "pago");
        ObjectId expectedId = new ObjectId();
        when(negocio.crearGrupo(grupo)).thenReturn(expectedId);

        // Act
        ObjectId result = negocio.crearGrupo(grupo);

        // Assert
        assertEquals(expectedId, result);
        verify(negocio).crearGrupo(grupo);
    }

    @Test
    public void testObtenerGrupoPorId() {
        // Arrange
        String id = "1";
        GrupoDTO grupoDTO = new GrupoDTO("jose", 123.23, "pago");
        when(negocio.obtenerGrupoPorId(id)).thenReturn(grupoDTO);

        // Act
        GrupoDTO result = negocio.obtenerGrupoPorId(id);

        // Assert
        assertEquals(grupoDTO, result);
        verify(negocio).obtenerGrupoPorId(id);
    }

    @Test
    public void testAgregarContacto() {
        // Arrange
        ContactoDTO contacto = new ContactoDTO("jose");
        String id = "1";
        when(negocio.agregarContacto(contacto, id)).thenReturn(true);

        // Act
        Boolean result = negocio.agregarContacto(contacto, id);

        // Assert
        assertTrue(result);
        verify(negocio).agregarContacto(contacto, id);
    }
    
}
