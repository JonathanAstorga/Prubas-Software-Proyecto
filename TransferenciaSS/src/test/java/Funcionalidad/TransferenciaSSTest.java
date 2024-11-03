/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Funcionalidad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import DTOs.PersonaDTO;
import DTOs.TarjetaDTO;
import DTOs.TransferenciaDTO;
import Objetos.Interfaces.IObjetoNegocioPersona;
import Objetos.Interfaces.IObjetoNegocioTarjeta;
import Objetos.Interfaces.IObjetoNegocioTransferencia;
import Funcionalidad.TransferenciaSS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author tacot
 */
public class TransferenciaSSTest {

    private TransferenciaSS transferenciaSS;
    private IObjetoNegocioTransferencia mockTransferencia;
    private IObjetoNegocioPersona mockPersona;
    private IObjetoNegocioTarjeta mockTarjeta;

    public TransferenciaSSTest() {
        
    }

    @BeforeEach
    public void setUp() {
        mockTransferencia = mock(IObjetoNegocioTransferencia.class);
        mockPersona = mock(IObjetoNegocioPersona.class);
        mockTarjeta = mock(IObjetoNegocioTarjeta.class);

        transferenciaSS = new TransferenciaSS(mockTransferencia, mockPersona, mockTarjeta);

    }

    /**
     * Test of realizarTransferencia method, of class TransferenciaSS.
     */
    @Test
    public void testRealizarTransferencia() {
        //arrange
        System.out.println("testRealizarTransferencia");
        
        TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
        
        transferenciaDTO.setNumeroCuentaDestinatario("123456789");

        when(mockTarjeta.obtenerPersonaDeTarjeta(any(TarjetaDTO.class))).thenReturn(new PersonaDTO());
        
        when(mockTransferencia.realizarTransferencia(transferenciaDTO)).thenReturn(true);

        boolean expResult = true;
        
        //act
        boolean resultado = transferenciaSS.realizarTransferencia(transferenciaDTO);

        //assert
        assertEquals(expResult, resultado);
        
        verify(mockTarjeta).obtenerPersonaDeTarjeta(any(TarjetaDTO.class));
        
        verify(mockTransferencia).realizarTransferencia(transferenciaDTO);

    }

}
