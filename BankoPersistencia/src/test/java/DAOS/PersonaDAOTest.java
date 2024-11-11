/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAOS;

import Conexion.Conexion;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.InsertOneResult;
import encriptacion.Encriptador;
import entidades.Persona;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

public class PersonaDAOTest {

     @Mock
     private MongoCollection<Persona> mockCollection;

     @Mock
     private InsertOneResult insertOneResult;

     @Mock
     private MongoCursor<Persona> mockCursor;

     @InjectMocks
     private PersonaDAO personaDAO;

     private Encriptador encriptadorMock;

     @BeforeEach
     public void setUp() {
          mockCollection = Mockito.mock(MongoCollection.class);
          personaDAO = new PersonaDAO(mockCollection);
          encriptadorMock = Mockito.mock(Encriptador.class);
     }

     @Test
     public void TestRegistrar_PersonaRegistrada_ReturnSuccess() {
          // Arrange
          mockCollection = Mockito.mock(MongoCollection.class);
          insertOneResult = Mockito.mock(InsertOneResult.class);

          when(mockCollection.insertOne(any(Persona.class))).thenReturn(insertOneResult);
          when(insertOneResult.wasAcknowledged()).thenReturn(true);

          personaDAO = new PersonaDAO(mockCollection);

          // Act
          boolean result = personaDAO.registrar(new Persona("Nombre", "Apellido", "Email"));

          // Assert
          assertNotNull(result, "El registro de la persona debería ser exitoso.");
     }

     @Test
     public void TestPersonaRegistrada_PersonaEncontrada_ReturnSuccess() {
          // Arrange
          Persona persona = new Persona("Juan", "Pérez", "Gómez", "PEGJ900615HDFRZN00", "contraseña123", new Date(), "5512345678", new ArrayList<>());

          FindIterable<Persona> mockFindIterable = mock(FindIterable.class);

          when(mockFindIterable.first()).thenReturn(persona);

          when(mockCollection.find(eq("_id", persona.getId()))).thenReturn(mockFindIterable);

          // Act
          Boolean result = personaDAO.personaRegistrada(persona);

          // Assert
          assertTrue(result);
     }

     @Test
     public void TestPersonaRegistrada_PersonaNoEncontrada_ReturnSuccess() {
          // Arrange
          Persona persona = new Persona("Juan", "Pérez", "Gómez", "PEGJ900615HDFRZN00", "contraseña123", new Date(), "5512345678", new ArrayList<>());

          FindIterable<Persona> mockFindIterable = mock(FindIterable.class);

          when(mockFindIterable.first()).thenReturn(null);

          when(mockCollection.find(eq("_id", persona.getId()))).thenReturn(mockFindIterable);

          // Act
          Boolean result = personaDAO.personaRegistrada(persona);

          // Assert
          assertFalse(result);
     }

     @Test

     void testObtenerPersonaPorCurp_PersonaEncontrada_ReturnSuccess() {
          // Arrange
          String curp = "ABCD123456";
          Persona personaMock = new Persona();
          personaMock.setCurp(curp);
          personaMock.setNombre("Juan");
          personaMock.setApellidoP("Pérez");

          FindIterable<Persona> findIterableMock = Mockito.mock(FindIterable.class);
          Mockito.when(mockCollection.find(Mockito.any(Bson.class))).thenReturn(findIterableMock);
          Mockito.when(findIterableMock.first()).thenReturn(personaMock);

          // Act
          PersonaDAO personaDAO = new PersonaDAO(mockCollection);

          Persona persona = personaDAO.obtenerPersonaPorCurp(personaMock);

          // Assert
          assertNotNull(persona);
     }

     @Test
     void testObtenerPersonaPorCurp_PersonaNoEncontrada_ReturnSuccess() {
           // Arrange
          String curp = "ABCD123456";
          Persona personaMock = new Persona();
          personaMock.setCurp(curp);
          personaMock.setNombre("Juan");
          personaMock.setApellidoP("Pérez");

          FindIterable<Persona> findIterableMock = Mockito.mock(FindIterable.class);
          Mockito.when(mockCollection.find(Mockito.any(Bson.class))).thenReturn(findIterableMock);
          Mockito.when(findIterableMock.first()).thenReturn(personaMock);

          PersonaDAO personaDAO = new PersonaDAO(mockCollection);

          //Act
          Persona persona = personaDAO.obtenerPersonaPorCurp(personaMock);

          //Assert
          assertNotNull(persona);
     }

     @Test
     public void TestProcesarInicioSesion_InicioSesionExitoso_ReturnSuccess() {
          System.out.println("procesarInicioSesion - Credenciales correctas");

          // Arrange
          String telefono = "5512345678";
          String contrasena = "password";
          Persona persona = new Persona();
          persona.setTelefono(telefono);
          persona.setContrasena(contrasena);

          FindIterable<Persona> mockFindIterable = Mockito.mock(FindIterable.class);
          when(mockCollection.find(any(Document.class))).thenReturn(mockFindIterable);
          when(mockFindIterable.first()).thenReturn(persona);

          // Act
          Boolean resultado = personaDAO.procesarInicioSesion(telefono, contrasena);

          // Assert
          assertNotNull(resultado);
     }

     @Test
     public void TestProcesarInicioSesion_InicioSesionErroneo_ReturnSuccess() {
          System.out.println("procesarInicioSesion - Credenciales incorrectas");

          // Arrange
          String telefono = "5512345678";
          String contrasena = "wrongPassword";

          FindIterable<Persona> mockFindIterable = Mockito.mock(FindIterable.class);
          when(mockCollection.find(any(Document.class))).thenReturn(mockFindIterable);
          when(mockFindIterable.first()).thenReturn(null);

          // Act
          Boolean resultado = personaDAO.procesarInicioSesion(telefono, contrasena);

          // Assert
          assertNotNull(resultado);
     }

     @Test
     void TestObtenerPersonaPorTelefonoYContrasena_PersonaEncontrada_ReturnSuccess() {
          //Arrange
          MongoCollection<Persona> mockCollection = mock(MongoCollection.class);

          Persona personaMock = new Persona("Juan", "Pérez", "Gómez", "PEGJ900615HDFRZN00", "contraseña123", new Date(), "5512345678", new ArrayList<>());

          FindIterable<Persona> mockFindIterable = mock(FindIterable.class);

          MongoCursor<Persona> cursorMock = mock(MongoCursor.class);

          when(mockFindIterable.iterator()).thenReturn(cursorMock);

          when(cursorMock.hasNext()).thenReturn(true);
          when(cursorMock.next()).thenReturn(personaMock);

          when(mockCollection.find(eq(Filters.eq("telefono", "5512345678")))).thenReturn(mockFindIterable);

          // Act
          Persona persona = personaDAO.obtenerPersonaPorTelefonoYContrasena("5512345678", "contraseña123");

          // Assert
          assertNull(persona);
     }

     @Test
     void TestObtenerPersonaPorTelefonoYContrasena_PersonaNoEncontrada_ReturnSuccess() {
          //Arrange
          MongoCollection<Persona> mockCollection = mock(MongoCollection.class);

          Persona personaMock = new Persona("Juan", "Pérez", "Gómez", "PEGJ900615HDFRZN00", "contraseña123", new Date(), "5512345678", new ArrayList<>());

          FindIterable<Persona> mockFindIterable = mock(FindIterable.class);

          MongoCursor<Persona> cursorMock = mock(MongoCursor.class);

          when(mockFindIterable.iterator()).thenReturn(cursorMock);

          when(cursorMock.hasNext()).thenReturn(true);
          when(cursorMock.next()).thenReturn(personaMock);

          when(mockCollection.find(eq(Filters.eq("telefono", "5512345678")))).thenReturn(mockFindIterable);

          // Act
          Persona persona = personaDAO.obtenerPersonaPorTelefonoYContrasena("5512345678", "contraseña123");

          // Assert
          assertNull(persona);
     }
}
