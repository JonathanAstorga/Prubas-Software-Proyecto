package Funcionalidad;

import DTOs.PersonaDTO;
import Objetos.Interfaces.IObjetoNegocioPersona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class IniciarSesionSSTest {

    @Mock
    private IObjetoNegocioPersona personaMock;

    @InjectMocks
    private IniciarSesionSS iniciarSesionSS;

    @BeforeEach
    void setUp() {
        // Inicializar los mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void procesarInicioSesion_SesionExitosa() {
        // Arrange
        String telefono = "1234567890";
        String contrasena = "password123";
        when(personaMock.procesarInicioSesion(telefono, contrasena)).thenReturn(true);

        // Act
        Boolean resultado = iniciarSesionSS.procesarInicioSesion(telefono, contrasena);

        // Assert
        assertTrue(resultado);
        verify(personaMock).procesarInicioSesion(telefono, contrasena);
    }

    @Test
    void procesarInicioSesion_SesionFallida() {
        // Arrange
        String telefono = "1234567890";
        String contrasena = "wrongPassword";
        when(personaMock.procesarInicioSesion(telefono, contrasena)).thenReturn(false);

        // Act
        Boolean resultado = iniciarSesionSS.procesarInicioSesion(telefono, contrasena);

        // Assert
        verify(personaMock).procesarInicioSesion(telefono, contrasena);
    }

    @Test
    void obtenerPersonaPorTelefonoYContrasena_PersonaEncontrada() {
        // Arrange
        String telefono = "1234567890";
        String contrasena = "password123";
        PersonaDTO personaEsperada = new PersonaDTO();
        personaEsperada.setCurp("CURP123");
        when(personaMock.obtenerPersonaPorTelefonoYContrasena(telefono, contrasena)).thenReturn(personaEsperada);

        // Act
        PersonaDTO resultado = iniciarSesionSS.obtenerPersonaPorTelefonoYContrasena(telefono, contrasena);

        // Assert
        assertEquals("CURP123", resultado.getCurp());
        verify(personaMock).obtenerPersonaPorTelefonoYContrasena(telefono, contrasena);
    }

    @Test
    void obtenerPersonaPorTelefonoYContrasena_PersonaNoEncontrada() {
        // Arrange
        String telefono = "1234567890";
        String contrasena = "password123";
        when(personaMock.obtenerPersonaPorTelefonoYContrasena(anyString(), anyString())).thenReturn(null);

        // Act
        PersonaDTO resultado = iniciarSesionSS.obtenerPersonaPorTelefonoYContrasena(telefono, contrasena);

        // Assert
        verify(personaMock).obtenerPersonaPorTelefonoYContrasena(telefono, contrasena);
    }
}
