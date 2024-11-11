/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Funcionalidad;

import DTOs.TarjetaDTO;
import DTOs.TransferenciaDTO;
import DTOs.tipoBancoDTO;
import DTOs.tipoTarjetaDTO;
import Objetos.Interfaces.IObjetoNegocioTransferencia;
import Objetos.ObjetoNegocioTransferencia;
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
public class MovimientosTransferenciasSSTest {

    @Mock
    private ObjetoNegocioTransferencia mockTransferencia;

    @InjectMocks
    private MovimientosTransferenciasSS movimientosSS;

    private TarjetaDTO tarjetaDTO;
    private Date fechaInicio;
    private Date fechaFin;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fechaInicio = new Date();
        fechaFin = new Date();
        tarjetaDTO = new TarjetaDTO("123232312", tipoTarjetaDTO.DEBITO, tipoBancoDTO.BANCOPPEL, 435.23, fechaInicio);
    }

    @Test
    void testObtenerTransferenciasEgreso() {
        // Arrange
        List<TransferenciaDTO> transferenciasEsperadas = new ArrayList<>();
        when(mockTransferencia.obtenerTransferenciasEgreso(any(TarjetaDTO.class), any(Date.class), any(Date.class))).thenReturn(transferenciasEsperadas);

        // Act
        List<TransferenciaDTO> resultado = movimientosSS.obtenerTransferenciasEgreso(tarjetaDTO, fechaInicio, fechaFin);

        // Assert
        assertEquals(transferenciasEsperadas, resultado);
        verify(mockTransferencia).obtenerTransferenciasEgreso(tarjetaDTO, fechaInicio, fechaFin);
    }

    @Test
    void testObtenerTransferenciasIngreso() {
        // Arrange
        List<TransferenciaDTO> transferenciasEsperadas = new ArrayList<>();
        when(mockTransferencia.obtenerTransferenciasIngreso(any(TarjetaDTO.class), any(Date.class), any(Date.class))).thenReturn(transferenciasEsperadas);

        // Act
        List<TransferenciaDTO> resultado = movimientosSS.obtenerTransferenciasIngreso(tarjetaDTO, fechaInicio, fechaFin);

        // Assert
        assertEquals(transferenciasEsperadas, resultado);
        verify(mockTransferencia).obtenerTransferenciasIngreso(tarjetaDTO, fechaInicio, fechaFin);
    }

    @Test
    void testObtenerTransferencias() {
        // Arrange
        List<TransferenciaDTO> transferenciasEsperadas = new ArrayList<>();
        when(mockTransferencia.obtenerTransferencias(any(TarjetaDTO.class), any(Date.class), any(Date.class))).thenReturn(transferenciasEsperadas);

        // Act
        List<TransferenciaDTO> resultado = movimientosSS.obtenerTransferencias(tarjetaDTO, fechaInicio, fechaFin);

        // Assert
        assertEquals(transferenciasEsperadas, resultado);
        verify(mockTransferencia).obtenerTransferencias(tarjetaDTO, fechaInicio, fechaFin);
    }

    @Test
    void testIngresosDelDia() {
        // Arrange
        Double ingresosEsperados = 240.0;
        when(mockTransferencia.ingresosDelDia(any(TarjetaDTO.class))).thenReturn(ingresosEsperados);

        // Act
        Double resultado = movimientosSS.ingresosDelDia(tarjetaDTO);

        // Assert
        assertEquals(ingresosEsperados, resultado);
        verify(mockTransferencia).ingresosDelDia(tarjetaDTO);
    }

    @Test
    void testEgresosDelDia() {
        // Arrange
        Double egresosEsperados = 330.0;
        when(mockTransferencia.egresosDelDia(any(TarjetaDTO.class))).thenReturn(egresosEsperados);

        // Act
        Double resultado = movimientosSS.egresosDelDia(tarjetaDTO);

        // Assert
        assertEquals(egresosEsperados, resultado);
        verify(mockTransferencia).egresosDelDia(tarjetaDTO);
    }

    @Test
    void testObtenerTransferenciasSinFecha() {
        // Arrange
        List<TransferenciaDTO> transferenciasEsperadas = new ArrayList<>();
        when(mockTransferencia.obtenerTransferenciasSinFecha(any(TarjetaDTO.class))).thenReturn(transferenciasEsperadas);

        // Act
        List<TransferenciaDTO> resultado = movimientosSS.obtenerTransferenciasSinFecha(tarjetaDTO);

        // Assert
        assertEquals(transferenciasEsperadas, resultado);
        verify(mockTransferencia).obtenerTransferenciasSinFecha(tarjetaDTO);
    }
}
