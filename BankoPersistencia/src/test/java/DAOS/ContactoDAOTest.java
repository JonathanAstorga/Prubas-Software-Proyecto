/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAOS;

import Conexion.Conexion;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import entidades.Contacto;
import entidades.Persona;
import entidades.Tarjeta;
import entidades.tipoBanco;
import entidades.tipoTarjeta;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.eclipse.persistence.annotations.OptimisticLocking;
import org.eclipse.persistence.internal.oxm.schema.model.Any;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Dell
 */
public class ContactoDAOTest {

    PersonaDAO pDAO;
    
    Conexion conexionMock;
    MongoCollection<Object> dbMock;
    
    
    ContactoDAO contactoDAO;
    
    UpdateResult updateResult;
    

    public ContactoDAOTest() {
        pDAO = new PersonaDAO();
        dbMock = Mockito.mock(MongoCollection.class);
        contactoDAO = new ContactoDAO(dbMock);
        updateResult = Mockito.mock(UpdateResult.class);
    } 
    

    @Test
    public void testAgregar_Boolean_ReturnSuccess() {
        //arrange
        System.out.println("Agregar Contacto en base de datos.");
        
        Persona persona = new Persona("PEGJ900615HDFRZN00"); //Persona a la que se le agregara el contacto
        Persona persona1 = pDAO.obtenerPersonaPorCurp(persona); //Persona a la que se le agregara el contacto

        Persona person = new Persona("SALM851225MDFRPR09"); //Persona que sera el contacto
        Persona persona2 = pDAO.obtenerPersonaPorCurp(person); //Persona que sera el contacto

        Persona pe = new Persona();

        //Se ocupa un Persona que este registrada en la base de datos.
        Contacto conta = new Contacto("MariaSiSi2", persona2.getNombre(), persona2.getApellidoP(), persona2.getApellidoM(),
                "1234567891",
                tipoBanco.BANCOPPEL);

        Mockito.when(dbMock.updateOne(Mockito.any(Bson.class), Mockito.any(Bson.class))).thenReturn(updateResult);

        
        //act
        boolean resultado = contactoDAO.agregar(persona1, conta);

        //assert
        assertTrue(resultado);
    }

    @Test
    public void testAgregar_ErrorAlAñadir_ReturnSuccess() {
        //arrange
        System.out.println("Agregar Contacto en base de datos.");

        Persona persona1 = new Persona("hola"); //Persona a la que se le agregara el contacto (No esta registrada)

        Persona person = new Persona("SALM851225MDFRPR09"); //Persona que sera el contacto
        Persona persona2 = pDAO.obtenerPersonaPorCurp(person); //Persona que sera el contacto

        //Se ocupa un Persona que este registrada en la base de datos.
        Contacto conta = new Contacto("MariaSiSi", persona2.getNombre(), persona2.getApellidoP(), persona2.getApellidoM(),
                "1234567891",
                tipoBanco.BANCOPPEL);

        //act
        boolean resultado = contactoDAO.agregar(persona1, conta);

        //assert
        assertFalse(resultado);
    }

    /**
     * Test of eliminar method, of class ContactoDAO.
     */
    @Test
    public void testEliminar_Boolean_ReturnSuccess() {
        //arrange
        System.out.println("Eliminar Contacto en base de datos.");

        Persona persona = new Persona("PEGJ900615HDFRZN00"); //Persona a la que se le agregara el contacto
        Persona persona1 = pDAO.obtenerPersonaPorCurp(persona); //Persona a la que se le agregara el contacto
        Contacto contacto = persona1.getListaContactos().getFirst();

        Mockito.when(dbMock.updateOne(Mockito.any(Bson.class), Mockito.any(Bson.class))).thenReturn(updateResult);

        //act
        boolean resultado = contactoDAO.eliminar(persona1, contacto);

        //assert
        assertFalse(resultado);
    }

    @Test
    public void testEliminar_ErrorAlEliminar_ReturnSuccess() {
        //arrange
        System.out.println("Eliminar Contacto en base de datos.");
        Contacto conta = new Contacto("hola");
        Persona persona = new Persona("PEGJ900615HDFRZN00"); //Persona a la que se le agregara el contacto
        
        //act
        boolean resultado = contactoDAO.eliminar(persona, conta);

        //assert
        assertFalse(resultado);

    }

    /**
     * Test of actualizar method, of class ContactoDAO.
     */
    @Test
    public void testActualizar_Boolean_ReturnSuccess() {
        //arrange
        System.out.println("Actualizar Contacto en base de datos.");

        Persona persona = new Persona("PEGJ900615HDFRZN00"); //Persona a la que se le agregara el contacto
        Persona persona1 = pDAO.obtenerPersonaPorCurp(persona); //Persona a la que se le agregara el contacto
        Contacto contactoNuevo = persona1.getListaContactos().getFirst();
        contactoNuevo.setAlias("PepePecas");

        Mockito.when(dbMock.updateOne(Mockito.any(Bson.class), Mockito.any(Bson.class))).thenReturn(updateResult);

        //act
        boolean resultado = contactoDAO.actualizar(persona1, persona1.getListaContactos().getFirst(), contactoNuevo);

        //assert
        assertTrue(resultado);
    }

    @Test
    public void testActualizar_ErrorAlActualizar_ReturnSuccess() {
        //arrange
        System.out.println("Actualizar Contacto en base de datos.");
        Contacto conta = new Contacto("Pablo");
        Contacto contaNuevo = new Contacto("Pepe");
        Persona persona = new Persona("PEGJ900615HDFRZN00"); //Persona a la que se le agregara el contacto
        Persona persona1 = pDAO.obtenerPersonaPorCurp(persona); //Persona a la que se le agregara el contacto

        //act
        boolean resultado = contactoDAO.actualizar(persona1, conta, contaNuevo);

        //assert
        assertFalse(resultado);
    }

    /**
     * Test of obtenerContactosPersona method, of class ContactoDAO.
     */
    @Test
    public void testObtenerContactosPersona_ListaDeContactos_ReturnSuccess() {
        //arrange
        System.out.println("obtenerContactosPersona");
        Persona persona = new Persona("PEGJ900615HDFRZN00"); //Persona a la que se le agregara el contacto
        Persona persona1 = pDAO.obtenerPersonaPorCurp(persona); //Persona a la que se le agregara el contacto
        List<Contacto> esperado = persona1.getListaContactos();

        //act
        List<Contacto> resultado = contactoDAO.obtenerContactosPersona(persona1);

        //assert
        assertEquals(esperado, resultado);
    }

    @Test
    public void testObtenerContactosPersona_ErrorListaDeContactos_ReturnSuccess() {
        //arrange
        System.out.println("obtenerContactosPersona");
        Persona persona = new Persona("PEGJ900615HDFRZN00"); //Persona a la que se le agregara el contacto
        Persona persona1 = pDAO.obtenerPersonaPorCurp(persona); //Persona a la que se le agregara el contacto
        List<Contacto> esperado = persona1.getListaContactos();

        //act
        List<Contacto> resultado = contactoDAO.obtenerContactosPersona(persona);

        //assert
        assertNotEquals(esperado, resultado);
    }

    /**
     * Test of obtenerContactoPersona method, of class ContactoDAO.
     */
    @Test
    public void testObtenerContactoPersona_Contacto_ReturnSuccess() {
        //arrange
        System.out.println("obtenerContactoPersona");
        Persona persona = new Persona("PEGJ900615HDFRZN00"); //Persona a la que se le agregara el contacto
        Persona persona1 = pDAO.obtenerPersonaPorCurp(persona); //Persona a la que se le agregara el contacto
        Contacto esperado = persona1.getListaContactos().getFirst();

        //act
        Contacto resultado = contactoDAO.obtenerContactoPersona(persona1, esperado);

        //assert
        assertEquals(esperado, resultado);
    }

    @Test
    public void testObtenerContactoPersona_ErrorAlObtenerContacto_ReturnSuccess() {
        //arrange
        System.out.println("obtenerContactoPersona");
        Persona persona = new Persona("PEGJ900615HDFRZN00"); //Persona a la que se le agregara el contacto
        Persona persona1 = pDAO.obtenerPersonaPorCurp(persona); //Persona a la que se le agregara el contacto
        Contacto esperado = persona1.getListaContactos().getFirst();

        //act
        Contacto resultado = contactoDAO.obtenerContactoPersona(persona1, persona1.getListaContactos().getLast());

        //assert
        assertNotEquals(esperado, resultado);
    }

    /**
     * Test of validaContactoPersona method, of class ContactoDAO.
     */
    @Test
    public void testValidaContactoPersona_Boolean_ReturnSuccess() {
        //arrange
        System.out.println("validaContactoPersona");
        Contacto contacto1 = new Contacto("Papa");
        Contacto contacto2 = new Contacto("Papa");

        //act
        boolean resultado = contactoDAO.validaContactoPersona(contacto1, contacto2);

        //assert
        assertTrue(resultado);

    }

    @Test
    public void testValidaContactoPersona_NoConciden_ReturnSuccess() {
        //arrange
        System.out.println("validaContactoPersona");
        Contacto contacto1 = new Contacto("Papa");
        Contacto contacto2 = new Contacto("Mama");

        //act
        boolean resultado = contactoDAO.validaContactoPersona(contacto1, contacto2);

        //assert
        assertFalse(resultado);

    }

}
