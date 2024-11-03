package Funcionalidad;

import DTOs.PersonaDTO;
import DTOs.TarjetaDTO;
import Objetos.Interfaces.IObjetoNegocioTarjeta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class TarjetaSSTest {

    @Mock
    private IObjetoNegocioTarjeta mockTarjeta;

    @InjectMocks
    private TarjetaSS tarjetaSS;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTarjetaDTOPorNumero() {
        // Arrange
        TarjetaDTO tarjetaDTO = new TarjetaDTO();
        TarjetaDTO expResult = new TarjetaDTO();
        
        when(mockTarjeta.obtenerTarjetaDTOPorNumero(any(TarjetaDTO.class))).thenReturn(expResult);

        // Act
        TarjetaDTO resultado = tarjetaSS.obtenerTarjetaDTOPorNumero(tarjetaDTO);

        // Assert
        assertEquals(expResult, resultado);
        verify(mockTarjeta).obtenerTarjetaDTOPorNumero(tarjetaDTO);
    }

    @Test
    void testObtenerTarjetasDTOPersona() {
        // Arrange
        PersonaDTO personaDTO = new PersonaDTO();
        TarjetaDTO tarjeta1 = new TarjetaDTO();
        TarjetaDTO tarjeta2 = new TarjetaDTO();
        personaDTO.setListaTarjetas(Arrays.asList(tarjeta1, tarjeta2));

        // Act
        List<TarjetaDTO> resultado = tarjetaSS.obtenerTarjetasDTOPersona(personaDTO);

        // Assert
        assertEquals(personaDTO.getListaTarjetas(), resultado);
    }

    @Test
    void testObtenerUltimaTarjetaDTOPersona() {
        // Arrange
        PersonaDTO personaDTO = new PersonaDTO();
        TarjetaDTO ultimaTarjetaEsperada = new TarjetaDTO();
        
        when(mockTarjeta.obtenerUltimaTarjetaDTOPersona(personaDTO)).thenReturn(ultimaTarjetaEsperada);

        // Act
        TarjetaDTO resultado = tarjetaSS.obtenerUltimaTarjetaDTOPersona(personaDTO);

        // Assert
        assertEquals(ultimaTarjetaEsperada, resultado);
        verify(mockTarjeta).obtenerUltimaTarjetaDTOPersona(personaDTO);
    }

    @Test
    void testObtenerPersonaDeTarjeta() {
        // Arrange
        TarjetaDTO tarjetaDTO = new TarjetaDTO();
        PersonaDTO personaEsperada = new PersonaDTO();
        
        when(mockTarjeta.obtenerPersonaDeTarjeta(any(TarjetaDTO.class))).thenReturn(personaEsperada);

        // Act
        PersonaDTO resultado = tarjetaSS.obtenerPersonaDeTarjeta(tarjetaDTO);

        // Assert
        assertEquals(personaEsperada, resultado);
        verify(mockTarjeta).obtenerPersonaDeTarjeta(tarjetaDTO);
    }

    @Test
    void testObtenerTodasLasTarjetasDeClientes() {
        // Arrange
        TarjetaDTO tarjeta1 = new TarjetaDTO();
        TarjetaDTO tarjeta2 = new TarjetaDTO();
        List<TarjetaDTO> tarjetasEsperadas = Arrays.asList(tarjeta1, tarjeta2);
        
        when(mockTarjeta.obtenerTodasLasTarjetasDeClientes()).thenReturn(tarjetasEsperadas);

        // Act
        List<TarjetaDTO> resultado = tarjetaSS.obtenerTodasLasTarjetasDeClientes();

        // Assert
        assertEquals(tarjetasEsperadas, resultado);
        verify(mockTarjeta).obtenerTodasLasTarjetasDeClientes();
    }
}
