/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAOS;

import Conexion.Conexion;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import entidades.Contacto;
import entidades.Grupo;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author favel
 */
public class GrupoDAOTest {

     ContactoDAO cDAO;
     Conexion conexionMock;
     MongoCollection<Grupo> dbMock;

     @Mock
     private MongoCollection<Document> mockCollection;

     @Mock
     private FindIterable<Document> mockFindIterable;

     @InjectMocks
     private GrupoDAO grupoDAO;

     private Document grupoDocumento;

     UpdateResult updateResult;

     @BeforeEach
     public void setUp() {
          dbMock = Mockito.mock(MongoCollection.class);
          grupoDAO = new GrupoDAO(dbMock);
          updateResult = Mockito.mock(UpdateResult.class);
     }

     @Test
     public void TestObtenerGrupoPorID_DocumentoEncontrado_ReturnSuccess() {
          System.out.println("obtenerGrupoPorID - Documento Encontrado");

          ObjectId id = new ObjectId();
          Grupo grupoEsperado = new Grupo();
          grupoEsperado.setId(id);

          // Configura el mock de FindIterable
          FindIterable<Grupo> mockFindIterable = Mockito.mock(FindIterable.class);

          // Configura el comportamiento simulado de dbMock y FindIterable
          when(dbMock.find(any(Document.class))).thenReturn(mockFindIterable);
          when(mockFindIterable.first()).thenReturn(grupoEsperado);

          // Ejecuta el método
          Grupo resultado = grupoDAO.obtenerGrupoPorID(id);

          // Verifica el resultado
          assertNotNull(resultado);
          assertEquals(grupoEsperado.getId(), resultado.getId());
     }

     @Test
     public void TestObtenerGrupoPorID_DocumentoNoEncontrado_ReturnSuccess() {
          System.out.println("obtenerGrupoPorID - Documento No Encontrado");

          ObjectId id = new ObjectId();

          // Configura el mock de FindIterable
          FindIterable<Grupo> mockFindIterable = Mockito.mock(FindIterable.class);

          // Configura el comportamiento simulado de dbMock y FindIterable
          when(dbMock.find(any(Document.class))).thenReturn(mockFindIterable);
          when(mockFindIterable.first()).thenReturn(null);

          // Ejecuta el método
          Grupo resultado = grupoDAO.obtenerGrupoPorID(id);

          // Verifica el resultado
          assertNull(resultado);
     }

     /**
      * Test of crearGrupo method, of class GrupoDAO.
      */
     @Test
     public void TestCrearGrupo_GrupoAgregado_ReturnSuccess() {
          System.out.println("crearGrupo - Grupo Agregado Exitosamente");

          Grupo grupo = new Grupo();
          ObjectId idEsperado = new ObjectId();
          grupo.setId(idEsperado);

          // Ejecuta el método
          ObjectId resultado = grupoDAO.crearGrupo(grupo);

          // Verifica el resultado
          assertEquals(idEsperado, resultado);

          // Verifica que el método insertOne fue llamado con el grupo
          verify(dbMock).insertOne(grupo);
     }

     /**
      * Test of agregarContacto method, of class GrupoDAO.
      */
     @Test
     public void TestAgregarContacto_ContactoAgregado_ReturnSuccess() {
          System.out.println("agregarContacto - Contacto Agregado Exitosamente");

          Contacto contacto = new Contacto();
          contacto.setNombre("Juan");
          contacto.setApellidoP("Perez");
          contacto.setApellidoM("Lopez");

          String idGrupo = new ObjectId().toString();

          // Configura el comportamiento simulado del update
          when(updateResult.getModifiedCount()).thenReturn(1L);
          when(dbMock.updateOne(any(Document.class), any(Bson.class))).thenReturn(updateResult);

          // Ejecuta el método
          Boolean resultado = grupoDAO.agregarContacto(contacto, idGrupo);

          // Verifica el resultado
          assertTrue(resultado);
     }
}
