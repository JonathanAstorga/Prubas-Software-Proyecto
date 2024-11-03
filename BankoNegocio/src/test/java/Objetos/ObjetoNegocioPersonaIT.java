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
public class ObjetoNegocioPersonaIT {
    
    IObjetoNegocioPersona negocio;
    
    
    public ObjetoNegocioPersonaIT(){
        negocio = new ObjetoNegocioPersona();
    }
   

    /**
     * Test of convertirDTOAEntidad method, of class ObjetoNegocioPersona.
     */
    @Test
    public void testConvertirDTOAEntidad_PersonaDTO() {
        //arrange
        ObjetoNegocioPersona dto = new ObjetoNegocioPersona();
        PersonaDTO persona = new PersonaDTO("123");
        Persona result;
        //act
        result = dto.convertirDTOAEntidad(persona);
        
        //assert
        assertEquals("123", result.getCurp());
    }

    /**
     * Test of convertirDTOAEntidadCURP method, of class ObjetoNegocioPersona.
     */
    @Test
    public void testConvertirDTOAEntidadCURP() {
       ObjetoNegocioPersona dto = new ObjetoNegocioPersona();
        Persona persona = new Persona("123");
        PersonaDTO result;
        //act
        result = dto.convertirEntidadADTOCURP(persona);
        
        //assert
        assertEquals("123", result.getCurp());
    }

    /**
     * Test of convertirEntidadADTOCURP method, of class ObjetoNegocioPersona.
     */
    @Test
    public void testConvertirEntidadADTOCURP() {
        //arrange
        ObjetoNegocioPersona dto = new ObjetoNegocioPersona();
        Persona persona = new Persona("123");
        PersonaDTO result;
        //act
        result = dto.convertirEntidadADTOCURP(persona);
        
        //assert
        assertEquals("123", result.getCurp());
    }

    /**
     * Test of convertirEntidadADTO method, of class ObjetoNegocioPersona.
     */
    @Test
    public void testConvertirEntidadADTO_Persona() {
         ObjetoNegocioPersona dto = new ObjetoNegocioPersona();
        Persona persona = new Persona("123");
        PersonaDTO result;
        //act
        result = dto.convertirEntidadADTOCURP(persona);
        
        //assert
        assertEquals("123", result.getCurp());
    }

    /**
     * Test of registrar method, of class ObjetoNegocioPersona.
     */
    @Test
    public void testRegistrar() {
        //arrange
        PersonaDTO persona = new PersonaDTO("123");
        Boolean result;
        //act
        result = negocio.registrar(persona);
        
        
        //assert
        assertTrue(result);
    }

    /**
     * Test of personaRegistrada method, of class ObjetoNegocioPersona.
     */
    @Test
    public void testPersonaRegistrada() {
        //arrange
        PersonaDTO persona = new PersonaDTO("123");
        Boolean result;
        //act
        result = negocio.registrar(persona);
        
        
        //assert
        assertTrue(result);
    }

    /**
     * Test of obtenerPersonaDTOPorCurp method, of class ObjetoNegocioPersona.
     */
    @Test
    public void testObtenerPersonaDTOPorCurp() {
        //arrange
        PersonaDTO persona = new PersonaDTO("123");
        PersonaDTO result;
        
        //act
        
        result = negocio.obtenerPersonaDTOPorCurp(persona);
        
        //assert
        assertEquals(persona.getCurp(), result.getCurp());
    }

    /**
     * Test of procesarInicioSesion method, of class ObjetoNegocioPersona.
     */
    @Test
    public void testProcesarInicioSesion() {
        //arrange
        String contra = "123";
        String user = "madero";
        Boolean result;
        //act
        result = negocio.procesarInicioSesion(user, contra);
        
        //assert
        assertTrue(result);
    }

    /**
     * Test of obtenerPersonaPorTelefonoYContrasena method, of class ObjetoNegocioPersona.
     */
    @Test
    public void testObtenerPersonaPorTelefonoYContrasena() {
        String contra = "123";
        String telefono = "6441";
        PersonaDTO result;
        //act
        result = negocio.obtenerPersonaPorTelefonoYContrasena(telefono, contra);
        
        //assert
        assertEquals(telefono, result.getTelefono());
    }

    /**
     * Test of convertirDTOAEntidad method, of class ObjetoNegocioPersona.
     */
    @Test
    public void testConvertirDTOAEntidad_ContactoDTO() {
        //arrange
        
        
        //act
        
        
        //assert
    }

    /**
     * Test of convertirEntidadADTO method, of class ObjetoNegocioPersona.
     */
    @Test
    public void testConvertirEntidadADTO_Contacto() {
        //arrange
        
        
        //act
        
        
        //assert
    }

    /**
     * Test of insertMasivo method, of class ObjetoNegocioPersona.
     */
    @Test
    public void testInsertMasivo() {
        //arrange
        
        
        //act
        
        
        //assert
    }
    
}
