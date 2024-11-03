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

/**
 *
 * @author jl4ma
 */
public class ObjetoNegocioGrupoIT {
    
    IObjetoNegocioGrupo negocio;
    
    public ObjetoNegocioGrupoIT(){
        negocio = new ObjetoNegocioGrupo();
    }
    /**
     * Test of convertirDTOAEntidad method, of class ObjetoNegocioGrupo.
     */
    @Test
    public void testConvertirDTOAEntidad() {
        //arrange
        ObjetoNegocioGrupo dto = new ObjetoNegocioGrupo();
        GrupoDTO grupo = new GrupoDTO("jose", 123.23, "pago");
        Grupo result;
        
        //act
        result = dto.convertirDTOAEntidad(grupo);
        
        //assert
        assertEquals("jose", result.getNombre());
    }

    /**
     * Test of convertirEntidadADTO method, of class ObjetoNegocioGrupo.
     */
    @Test
    public void testConvertirEntidadADTO() {
        //arrange
        ObjetoNegocioGrupo dto = new ObjetoNegocioGrupo();
        Grupo grupo = new Grupo("jose", 123.23, "pago");
        GrupoDTO result;
        
        //act
        result = dto.convertirEntidadADTO(grupo);
        
        //assert
        assertEquals("jose", result.getNombre());
    }

    /**
     * Test of crearGrupo method, of class ObjetoNegocioGrupo.
     */
    @Test
    public void testCrearGrupo() {
        //arrange
        GrupoDTO grupo = new GrupoDTO("jose", 123.23, "pago");
        ObjectId result;
        
        //act
        result = negocio.crearGrupo(grupo);
        
        //assert
        assertEquals(result, result);
    }

    /**
     * Test of obtenerGrupoPorId method, of class ObjetoNegocioGrupo.
     */
    @Test
    public void testObtenerGrupoPorId() {
        //arrange
        String id = "1";
        GrupoDTO result;
        
        //act
        result = negocio.obtenerGrupoPorId(id);
        
        //assert
        assertEquals(result, result);
    }

    /**
     * Test of agregarContacto method, of class ObjetoNegocioGrupo.
     */
    @Test
    public void testAgregarContacto() {
        //arrange
        ContactoDTO contacto = new ContactoDTO("jose");
        String id = "1";
        Boolean result;
        
        //act
        result = negocio.agregarContacto(contacto, id);
        
        //assert
        assertTrue(result);
    }
    
}
