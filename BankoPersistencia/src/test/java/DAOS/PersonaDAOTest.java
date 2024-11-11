/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAOS;

import entidades.Persona;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author favel
 */
public class PersonaDAOTest {
     
     public PersonaDAOTest() {
     }

     /**
      * Test of registrar method, of class PersonaDAO.
      */
     @Test
     public void testRegistrar() {
          System.out.println("registrar");
          Persona persona = null;
          PersonaDAO instance = new PersonaDAO();
          Boolean expResult = null;
          Boolean result = instance.registrar(persona);
          assertEquals(expResult, result);
          // TODO review the generated test code and remove the default call to fail.
          fail("The test case is a prototype.");
     }

     /**
      * Test of personaRegistrada method, of class PersonaDAO.
      */
     @Test
     public void testPersonaRegistrada() {
          System.out.println("personaRegistrada");
          Persona persona = null;
          PersonaDAO instance = new PersonaDAO();
          boolean expResult = false;
          boolean result = instance.personaRegistrada(persona);
          assertEquals(expResult, result);
          // TODO review the generated test code and remove the default call to fail.
          fail("The test case is a prototype.");
     }

     /**
      * Test of obtenerPersonaPorCurp method, of class PersonaDAO.
      */
     @Test
     public void testObtenerPersonaPorCurp() {
          System.out.println("obtenerPersonaPorCurp");
          Persona persona = null;
          PersonaDAO instance = new PersonaDAO();
          Persona expResult = null;
          Persona result = instance.obtenerPersonaPorCurp(persona);
          assertEquals(expResult, result);
          // TODO review the generated test code and remove the default call to fail.
          fail("The test case is a prototype.");
     }

     /**
      * Test of obtenerTodasLasPersonas method, of class PersonaDAO.
      */
     @Test
     public void testObtenerTodasLasPersonas() {
          System.out.println("obtenerTodasLasPersonas");
          PersonaDAO instance = new PersonaDAO();
          List<Persona> expResult = null;
          List<Persona> result = instance.obtenerTodasLasPersonas();
          assertEquals(expResult, result);
          // TODO review the generated test code and remove the default call to fail.
          fail("The test case is a prototype.");
     }

     /**
      * Test of procesarInicioSesion method, of class PersonaDAO.
      */
     @Test
     public void testProcesarInicioSesion() {
          System.out.println("procesarInicioSesion");
          String telefono = "";
          String contra = "";
          PersonaDAO instance = new PersonaDAO();
          Boolean expResult = null;
          Boolean result = instance.procesarInicioSesion(telefono, contra);
          assertEquals(expResult, result);
          // TODO review the generated test code and remove the default call to fail.
          fail("The test case is a prototype.");
     }

     /**
      * Test of obtenerPersonaPorTelefonoYContrasena method, of class PersonaDAO.
      */
     @Test
     public void testObtenerPersonaPorTelefonoYContrasena() {
          System.out.println("obtenerPersonaPorTelefonoYContrasena");
          String telefono = "";
          String contra = "";
          PersonaDAO instance = new PersonaDAO();
          Persona expResult = null;
          Persona result = instance.obtenerPersonaPorTelefonoYContrasena(telefono, contra);
          assertEquals(expResult, result);
          // TODO review the generated test code and remove the default call to fail.
          fail("The test case is a prototype.");
     }

     /**
      * Test of insertMasivo method, of class PersonaDAO.
      */
     @Test
     public void testInsertMasivo() {
          System.out.println("insertMasivo");
          PersonaDAO instance = new PersonaDAO();
          Boolean expResult = null;
          Boolean result = instance.insertMasivo();
          assertEquals(expResult, result);
          // TODO review the generated test code and remove the default call to fail.
          fail("The test case is a prototype.");
     }
     
}
