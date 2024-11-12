/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Funcionalidad;

import DTOs.PersonaDTO;
import DTOs.TarjetaDTO;
import Objetos.Interfaces.IObjetoNegocioTarjeta;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author tacot
 */
public class MostrarTarjetasSSTest {
    
    @Mock
    IObjetoNegocioTarjeta tarjeta;
    
    @InjectMocks
    MostrarTarjetasSS mostrarTarjetasSS;
    
    private PersonaDTO persona;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        persona = new PersonaDTO();
    }

    /**
     * Test of obtenerTarjetasDTOPersona method, of class MostrarTarjetasSS.
     */
    @Test
    public void testObtenerTarjetasDTOPersona() {
        // Arrange
        List<TarjetaDTO> tarjetasEsperadas = new ArrayList<>();
        when(tarjeta.obtenerTarjetasDTOPersona(any(PersonaDTO.class))).thenReturn(tarjetasEsperadas);

        // Act
        List<TarjetaDTO> resultado = mostrarTarjetasSS.obtenerTarjetasDTOPersona(persona);

        // Assert
        assertEquals(tarjetasEsperadas, resultado);
        verify(tarjeta).obtenerTarjetasDTOPersona(persona);
    }
    
}
