/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Funcionalidad;

import DTOs.ContactoDTO;
import DTOs.PersonaDTO;
import DTOs.tipoBancoDTO;
import Objetos.Interfaces.IObjetoNegocioContacto;
import Objetos.Interfaces.IObjetoNegocioPersona;
import java.util.ArrayList;
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
public class MostrarContactoSSTest {

    @Mock
    private IObjetoNegocioContacto contacto;

    @Mock
    private IObjetoNegocioPersona persona;

    @InjectMocks
    private MostrarContactoSS mostrarContactoSS;

    private PersonaDTO personaDTO;
    private ContactoDTO contactoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        personaDTO = new PersonaDTO();
        personaDTO.setCurp("CURP123");

        contactoDTO = new ContactoDTO("Alias123", "jesus", "morales", "rojas", "223423443", tipoBancoDTO.BANCOPPEL);
    }

    @Test
    void obtenerContactosDTOPersona() {
        // Arrange
        List<ContactoDTO> listaEsperada = new ArrayList<>();
        listaEsperada.add(contactoDTO);
        when(contacto.obtenerContactosDTOPersona(any(PersonaDTO.class))).thenReturn(listaEsperada);

        // Act
        List<ContactoDTO> resultado = mostrarContactoSS.obtenerContactosDTOPersona(personaDTO);

        // Assert
        assertEquals("Alias123", resultado.get(0).getAlias());
        verify(contacto).obtenerContactosDTOPersona(any(PersonaDTO.class));
    }

    @Test
    void obtenerContactoDTOPersona() {

        // Arrange 
        ContactoDTO contactoEsperado = new ContactoDTO("Alias123");
        PersonaDTO personaEsperda = new PersonaDTO("CURP123");
        List<ContactoDTO> listaEsperada = new ArrayList<>();
        listaEsperada.add(contactoEsperado);
        personaEsperda.setListaContactos(listaEsperada);
        when(contacto.obtenerContactoDTOPersona(any(PersonaDTO.class), any(ContactoDTO.class))).thenReturn(contactoEsperado);
        when(persona.obtenerPersonaDTOPorCurp(personaDTO)).thenReturn(personaEsperda);
        // Act 
        ContactoDTO resultado = mostrarContactoSS.obtenerContactoDTOPersona(personaDTO, contactoDTO);
        // Assert 
        assertNotNull(resultado);
        assertEquals("Alias123", resultado.getAlias());
        verify(contacto).obtenerContactoDTOPersona(any(PersonaDTO.class), any(ContactoDTO.class));
        verify(persona).obtenerPersonaDTOPorCurp(personaDTO);
    }

    @Test
    void validaMuestra() {

        // Arrange 
        List<ContactoDTO> listaContactos = new ArrayList<>();

        ContactoDTO contactoEsperado = new ContactoDTO("Alias123");
        listaContactos.add(contactoEsperado);

        PersonaDTO personaBuscada = new PersonaDTO("CURP123");
        personaBuscada.setListaContactos(listaContactos);

        when(persona.obtenerPersonaDTOPorCurp(any(PersonaDTO.class))).thenReturn(personaBuscada);

        // Act 
        Boolean resultado = mostrarContactoSS.validaMuestra(personaBuscada, contactoEsperado);

        // Assert 
        assertTrue(resultado);
        verify(persona).obtenerPersonaDTOPorCurp(any(PersonaDTO.class));
    }

}
