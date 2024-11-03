/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Objetos;

import DTOs.TarjetaDTO;
import DTOs.TransferenciaDTO;
import entidades.Tarjeta;
import entidades.Transferencia;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jl4ma
 */
public class ObjetoNegocioTransferenciaIT {
    
    public ObjetoNegocioTransferenciaIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of imprimirPDF method, of class ObjetoNegocioTransferencia.
     */
    @Test
    public void testImprimirPDF() {
        System.out.println("imprimirPDF");
        TarjetaDTO tarjetaDTO = null;
        Date fechaInicio = null;
        Date fechaFin = null;
        boolean ingresos = false;
        boolean egresos = false;
        ObjetoNegocioTransferencia instance = new ObjetoNegocioTransferencia();
        ByteArrayOutputStream expResult = null;
        ByteArrayOutputStream result = instance.imprimirPDF(tarjetaDTO, fechaInicio, fechaFin, ingresos, egresos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertirDTOAEntidad method, of class ObjetoNegocioTransferencia.
     */
    @Test
    public void testConvertirDTOAEntidad_TransferenciaDTO() {
        System.out.println("convertirDTOAEntidad");
        TransferenciaDTO transferenciaDTO = null;
        ObjetoNegocioTransferencia instance = new ObjetoNegocioTransferencia();
        Transferencia expResult = null;
        Transferencia result = instance.convertirDTOAEntidad(transferenciaDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertirEntidadADTO method, of class ObjetoNegocioTransferencia.
     */
    @Test
    public void testConvertirEntidadADTO_Transferencia() {
        System.out.println("convertirEntidadADTO");
        Transferencia transferencia = null;
        ObjetoNegocioTransferencia instance = new ObjetoNegocioTransferencia();
        TransferenciaDTO expResult = null;
        TransferenciaDTO result = instance.convertirEntidadADTO(transferencia);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of realizarTransferencia method, of class ObjetoNegocioTransferencia.
     */
    @Test
    public void testRealizarTransferencia() {
        System.out.println("realizarTransferencia");
        TransferenciaDTO transferenciaDTO = null;
        ObjetoNegocioTransferencia instance = new ObjetoNegocioTransferencia();
        boolean expResult = false;
        boolean result = instance.realizarTransferencia(transferenciaDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertirDTOAEntidad method, of class ObjetoNegocioTransferencia.
     */
    @Test
    public void testConvertirDTOAEntidad_TarjetaDTO() {
        System.out.println("convertirDTOAEntidad");
        TarjetaDTO tarjetaDTO = null;
        ObjetoNegocioTransferencia instance = new ObjetoNegocioTransferencia();
        Tarjeta expResult = null;
        Tarjeta result = instance.convertirDTOAEntidad(tarjetaDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertirEntidadADTO method, of class ObjetoNegocioTransferencia.
     */
    @Test
    public void testConvertirEntidadADTO_Tarjeta() {
        System.out.println("convertirEntidadADTO");
        Tarjeta tarjeta = null;
        ObjetoNegocioTransferencia instance = new ObjetoNegocioTransferencia();
        TarjetaDTO expResult = null;
        TarjetaDTO result = instance.convertirEntidadADTO(tarjeta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerTransferenciasEgreso method, of class ObjetoNegocioTransferencia.
     */
    @Test
    public void testObtenerTransferenciasEgreso() {
        System.out.println("obtenerTransferenciasEgreso");
        TarjetaDTO tarjetaDTO = null;
        Date fechaInicio = null;
        Date fechaFin = null;
        ObjetoNegocioTransferencia instance = new ObjetoNegocioTransferencia();
        List<TransferenciaDTO> expResult = null;
        List<TransferenciaDTO> result = instance.obtenerTransferenciasEgreso(tarjetaDTO, fechaInicio, fechaFin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerTransferenciasIngreso method, of class ObjetoNegocioTransferencia.
     */
    @Test
    public void testObtenerTransferenciasIngreso() {
        System.out.println("obtenerTransferenciasIngreso");
        TarjetaDTO tarjetaDTO = null;
        Date fechaInicio = null;
        Date fechaFin = null;
        ObjetoNegocioTransferencia instance = new ObjetoNegocioTransferencia();
        List<TransferenciaDTO> expResult = null;
        List<TransferenciaDTO> result = instance.obtenerTransferenciasIngreso(tarjetaDTO, fechaInicio, fechaFin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerTransferencias method, of class ObjetoNegocioTransferencia.
     */
    @Test
    public void testObtenerTransferencias() {
        System.out.println("obtenerTransferencias");
        TarjetaDTO tarjetaDTO = null;
        Date fechaInicio = null;
        Date fechaFin = null;
        ObjetoNegocioTransferencia instance = new ObjetoNegocioTransferencia();
        List<TransferenciaDTO> expResult = null;
        List<TransferenciaDTO> result = instance.obtenerTransferencias(tarjetaDTO, fechaInicio, fechaFin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ingresosDelDia method, of class ObjetoNegocioTransferencia.
     */
    @Test
    public void testIngresosDelDia() {
        System.out.println("ingresosDelDia");
        TarjetaDTO tarjetaDTO = null;
        ObjetoNegocioTransferencia instance = new ObjetoNegocioTransferencia();
        Double expResult = null;
        Double result = instance.ingresosDelDia(tarjetaDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of egresosDelDia method, of class ObjetoNegocioTransferencia.
     */
    @Test
    public void testEgresosDelDia() {
        System.out.println("egresosDelDia");
        TarjetaDTO tarjetaDTO = null;
        ObjetoNegocioTransferencia instance = new ObjetoNegocioTransferencia();
        Double expResult = null;
        Double result = instance.egresosDelDia(tarjetaDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerTransferenciasSinFecha method, of class ObjetoNegocioTransferencia.
     */
    @Test
    public void testObtenerTransferenciasSinFecha() {
        System.out.println("obtenerTransferenciasSinFecha");
        TarjetaDTO tarjetaDTO = null;
        ObjetoNegocioTransferencia instance = new ObjetoNegocioTransferencia();
        List<TransferenciaDTO> expResult = null;
        List<TransferenciaDTO> result = instance.obtenerTransferenciasSinFecha(tarjetaDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
