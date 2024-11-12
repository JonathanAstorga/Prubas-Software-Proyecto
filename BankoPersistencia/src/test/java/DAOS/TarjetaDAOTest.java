/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAOS;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.UpdateResult;
import entidades.Persona;
import entidades.Tarjeta;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class TarjetaDAOTest {

     private MongoCollection<Persona> mockCollection;
     private MongoCursor<Persona> mockCursor;

     private TarjetaDAO tarjetaDAO;

     @BeforeEach
     public void setUp() {
          mockCollection = Mockito.mock(MongoCollection.class);
          mockCursor = Mockito.mock(MongoCursor.class);
          tarjetaDAO = new TarjetaDAO(mockCollection);
     }

     @Test
     public void TestObtenerTarjetasPersona_ObjetoPersonaRecibidaNull_ReturnSuccess() {
          // Arrange
          Persona persona = null;

          // Act
          List<Tarjeta> resultado = tarjetaDAO.obtenerTarjetasPersona(persona);

          // Assert
          assertTrue(resultado.isEmpty());
     }

     @Test
     public void TestObtenerTarjetasPersona_ConsultaPersonaNull_ReturnSuccess() {
          // Arrange
          Persona persona = new Persona();
          persona.setId(new ObjectId());

          // Mock behavior to simulate the database returning null (no document found)
          FindIterable<Persona> mockFindIterable = Mockito.mock(FindIterable.class);
          when(mockCollection.find(any(Document.class))).thenReturn(mockFindIterable);
          when(mockFindIterable.first()).thenReturn(null);

          // Act
          List<Tarjeta> resultado = tarjetaDAO.obtenerTarjetasPersona(persona);

          // Assert
          assertTrue(resultado.isEmpty());
     }

     @Test
     public void TestObtenerTarjetasPersona_ConsultaPersonaEncontrada_ReturnSuccess() {
          // Arrange
          Persona persona = new Persona();
          persona.setId(new ObjectId());

          Persona personaEncontrada = new Persona();
          personaEncontrada.setId(persona.getId());
          personaEncontrada.setListaTarjetas(new ArrayList<>());

          FindIterable<Persona> mockFindIterable = Mockito.mock(FindIterable.class);
          when(mockCollection.find(any(Document.class))).thenReturn(mockFindIterable);
          when(mockFindIterable.first()).thenReturn(personaEncontrada);

          // Act
          List<Tarjeta> resultado = tarjetaDAO.obtenerTarjetasPersona(persona);

          // Assert
          assertNotNull(resultado);
          assertEquals(personaEncontrada.getListaTarjetas().size(), resultado.size());
     }

     @Test
     public void TestObtenerUltimaTarjetaPersona_SinTarjetas_ReturnSuccess() {
          // Arrange
          Persona persona = new Persona();
          persona.setListaTarjetas(new ArrayList<>());

          // Act
          Tarjeta resultado = tarjetaDAO.obtenerUltimaTarjetaPersona(persona);

          // Assert
          assertNull(resultado);
     }

     @Test
     public void TestObtenerUltimaTarjetaPersona_ConTarjetas_ReturnSuccess() {
          // Arrange
          List<Tarjeta> tarjetas = new ArrayList<>();
          Tarjeta tarjeta = new Tarjeta();
          tarjetas.add(tarjeta);

          Persona persona = new Persona();
          persona.setListaTarjetas(tarjetas);

          // Act
          Tarjeta resultado = tarjetaDAO.obtenerUltimaTarjetaPersona(persona);

          // Assert
          assertEquals(tarjeta, resultado);
     }

     @Test
     public void TestObtenerTarjetaPorNumero_TarjetaNoCoincide_ReturnSuccess() {
          // Arrange
          Tarjeta tarjeta = new Tarjeta();
          tarjeta.setNumeroCuenta("12345");

          List<Persona> personas = new ArrayList<>();

          // Mock behavior to simulate the database returning an empty list
          FindIterable<Persona> mockFindIterable = Mockito.mock(FindIterable.class);
          MongoCursor<Persona> mockCursor = Mockito.mock(MongoCursor.class);

          when(mockCollection.find(any(Bson.class))).thenReturn(mockFindIterable);
          when(mockFindIterable.iterator()).thenReturn(mockCursor);
          when(mockCursor.hasNext()).thenReturn(false);  // Simulate no results

          // Act
          Tarjeta resultado = tarjetaDAO.obtenerTarjetaPorNumero(tarjeta);

          // Assert
          assertNull(resultado);
     }

     @Test
     public void TestObtenerTarjetaPorNumero_TarjetaCoincide_ReturnSuccess() {
          // Arrange
          Tarjeta tarjeta = new Tarjeta();
          tarjeta.setNumeroCuenta("12345");

          Tarjeta tarjetaCoincidente = new Tarjeta();
          tarjetaCoincidente.setNumeroCuenta("12345");

          Persona persona = new Persona();
          List<Tarjeta> tarjetas = new ArrayList<>();
          tarjetas.add(tarjetaCoincidente);
          persona.setListaTarjetas(tarjetas);

          FindIterable<Persona> mockFindIterable = Mockito.mock(FindIterable.class);
          MongoCursor<Persona> mockCursor = Mockito.mock(MongoCursor.class);

          when(mockCollection.find(any(Bson.class))).thenReturn(mockFindIterable);
          when(mockFindIterable.iterator()).thenReturn(mockCursor);
          when(mockCursor.hasNext()).thenReturn(true).thenReturn(false);
          when(mockCursor.next()).thenReturn(persona);

          // Act
          Tarjeta resultado = tarjetaDAO.obtenerTarjetaPorNumero(tarjeta);

          // Assert
          assertNotNull(resultado);
          assertEquals(tarjetaCoincidente.getNumeroCuenta(), resultado.getNumeroCuenta());
     }

     @Test
     public void TestGuardar_TarjetaGuardadaAPersona_ReturnSuccess() {
          // Arrange
          Persona persona = new Persona();
          persona.setId(new ObjectId());

          Tarjeta tarjeta = new Tarjeta();

          // Act
          tarjetaDAO.guardar(persona, tarjeta);

          // Assert
          verify(mockCollection).updateOne(any(Bson.class), any(Bson.class));
     }

     @Test
     public void TestActualizarTarjeta_TarjetaActualizada_ReturnSuccess() {
          // Arrange
          Persona persona = new Persona();
          persona.setId(new ObjectId());

          Tarjeta tarjeta = new Tarjeta();
          tarjeta.setId(new ObjectId());

          // Act
          tarjetaDAO.actualizarTarjeta(persona, tarjeta);

          // Assert
          verify(mockCollection).updateOne(any(Bson.class), any(Bson.class));
     }

     @Test
     public void TestEliminarTarjeta_TarjetaEliminada_ReturnSuccess() {
          // Arrange 
          Persona persona = new Persona();
          persona.setId(new ObjectId());

          Tarjeta tarjeta = new Tarjeta();
          tarjeta.setId(new ObjectId());

          //Act
          tarjetaDAO.eliminar(persona, tarjeta);

          //Assert
          UpdateResult updateOne = verify(mockCollection).updateOne(any(Bson.class), any(Bson.class));
     }

     @Test
     public void TestObtenerPersonaDeTarjeta_PersonaEncontrada_ReturnSuccess() {
          // Arrange
          Tarjeta tarjeta = new Tarjeta();
          tarjeta.setNumeroCuenta("12345");

          Persona persona = new Persona();
          persona.setId(new ObjectId());

          FindIterable<Persona> mockFindIterable = Mockito.mock(FindIterable.class);
          when(mockCollection.find(any(Bson.class))).thenReturn(mockFindIterable);
          when(mockFindIterable.first()).thenReturn(persona);

          // Act
          Persona resultado = tarjetaDAO.obtenerPersonaDeTarjeta(tarjeta);

          // Assert
          assertNotNull(resultado);
          assertEquals(persona.getId(), resultado.getId());
     }

     @Test
     public void TestObtenerPersonaDeTarjeta_PersonaNoEncontrada_ReturnSuccess() {
          // Arrange
          Tarjeta tarjeta = new Tarjeta();
          tarjeta.setNumeroCuenta("12345");

          FindIterable<Persona> mockFindIterable = Mockito.mock(FindIterable.class);
          when(mockCollection.find(any(Bson.class))).thenReturn(mockFindIterable);
          when(mockFindIterable.first()).thenReturn(null);

          // Act
          Persona resultado = tarjetaDAO.obtenerPersonaDeTarjeta(tarjeta);

          // Assert
          assertNull(resultado);
     }

}
