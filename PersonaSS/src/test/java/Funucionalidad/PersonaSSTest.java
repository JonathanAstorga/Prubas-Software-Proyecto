/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Funucionalidad;

import DTOs.PersonaDTO;
import Objetos.Interfaces.IObjetoNegocioPersona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author tacot
 */
public class PersonaSSTest {
    @Mock
    private IObjetoNegocioPersona mockPersona;

    @InjectMocks
    private PersonaSS personaSS;
    private PersonaDTO personaDTO;

    
    public PersonaSSTest() {
    }
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        personaDTO = new PersonaDTO();
        personaDTO.setCurp("CURP1234");
        personaDTO.setNombre("Jesus");
        personaDTO.setApellidoP("Morales");
        personaDTO.setTelefono("1234567890");
    }


    @Test
    public void testRegistrarPersona() {
        //arrange
        when(mockPersona.registrar(personaDTO)).thenReturn(true);
        //act
        Boolean resultado = personaSS.registrar(personaDTO);
        //assert
        assertTrue(resultado);
        verify(mockPersona).registrar(personaDTO);
    }
    
    @Test
    public void testPersonaRegistrada() {
        //act
        when(mockPersona.personaRegistrada(personaDTO)).thenReturn(true);
        //act
        Boolean resultado = personaSS.personaRegistrada(personaDTO);
        //assert
        assertTrue(resultado);
        verify(mockPersona).personaRegistrada(personaDTO);
    }

    @Test
    public void testObtenerPersonaDTOPorCurp() {
        //act
        when(mockPersona.obtenerPersonaDTOPorCurp(personaDTO)).thenReturn(personaDTO);
        //act
        PersonaDTO personaBuscada = personaSS.obtenerPersonaDTOPorCurp(personaDTO);
        //assert
        assertEquals("CURP1234", personaBuscada.getCurp());
        verify(mockPersona).obtenerPersonaDTOPorCurp(personaDTO);
    }

    @Test
    public void testInsertMasivo() {
        //act
        when(mockPersona.insertMasivo()).thenReturn(true);
        //act
        Boolean resultado = personaSS.insertMasivo();
        //assert
        assertTrue(resultado);
        verify(mockPersona).insertMasivo();
    }
    
}
