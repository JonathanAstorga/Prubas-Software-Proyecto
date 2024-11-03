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

/**
 *
 * @author jl4ma
 */
public class ObjetoNegocioContactoIT {
    
    IObjetoNegocioContacto negocio;
    
    public ObjetoNegocioContactoIT(){
       this.negocio = new ObjetoNegocioContacto();
    }

    /**
     * Test of convertirDTOAEntidad method, of class ObjetoNegocioContacto.
     */
    @Test
    public void testConvertirDTOAEntidad() {
        //arrange
        ObjetoNegocioContacto dtos = new ObjetoNegocioContacto();
        ContactoDTO contactoDTO = new ContactoDTO("jose");
        Contacto result;
        //act
        result = dtos.convertirDTOAEntidad(contactoDTO);
        
        //assert
        assertEquals("jose", result.getAlias());
    }

    /**
     * Test of convertirEntidadADTO method, of class ObjetoNegocioContacto.
     */
    @Test
    public void testConvertirEntidadADTO() {
        //arrange
        ObjetoNegocioContacto dtos = new ObjetoNegocioContacto();
        Contacto contacto = new Contacto("jose");
        ContactoDTO result;
        //act
        result = dtos.convertirEntidadADTO(contacto);
        
        //assert
        assertEquals("jose", result.getAlias());
    }

    /**
     * Test of agregar method, of class ObjetoNegocioContacto.
     */
    @Test
    public void testAgregar() {
        //arrange
        PersonaDTO persona = new PersonaDTO("123");
        ContactoDTO contacto = new ContactoDTO("jose");
        Boolean result;
        //act
        result = negocio.agregar(persona, contacto);
        
        //assert
        assertTrue(result);
    }

    /**
     * Test of eliminar method, of class ObjetoNegocioContacto.
     */
    @Test
    public void testEliminar() {
         //arrange
        PersonaDTO persona = new PersonaDTO("123");
        ContactoDTO contacto = new ContactoDTO("jose");
        Boolean result;
        //act
        result = negocio.eliminar(persona, contacto);
        
        //assert
        assertTrue(result);
    }

    /**
     * Test of actualizar method, of class ObjetoNegocioContacto.
     */
    @Test
    public void testActualizar() {
         //arrange
        PersonaDTO persona = new PersonaDTO("123");
        ContactoDTO contacto = new ContactoDTO("jose");
        ContactoDTO contacto2 = new ContactoDTO("Luis");
        Boolean result;
        //act
        result = negocio.actualizar(persona, contacto, contacto2);
        
        //assert
        assertTrue(result);
    }

    /**
     * Test of obtenerContactosDTOPersona method, of class ObjetoNegocioContacto.
     */
    @Test
    public void testObtenerContactosDTOPersona() {
        //arrange
        PersonaDTO persona = new PersonaDTO("123");
        List<ContactoDTO> result;
        List<ContactoDTO> prueba = new ArrayList<>();
        
        //act
        result = negocio.obtenerContactosDTOPersona(persona);
        prueba.add(new ContactoDTO("jose"));
        
        //assert
        assertEquals(prueba.get(0).getAlias(), result.get(0).getAlias());
    }

    /**
     * Test of obtenerContactoDTOPersona method, of class ObjetoNegocioContacto.
     */
    @Test
    public void testObtenerContactoDTOPersona() {
        //arrange
        PersonaDTO persona = new PersonaDTO("123");
        ContactoDTO contacto = new ContactoDTO("jose");
        ContactoDTO result;
        
        //act
        result = negocio.obtenerContactoDTOPersona(persona, contacto);
        
        //assert
        assertEquals("jose", result.getAlias());
    }
    
}
