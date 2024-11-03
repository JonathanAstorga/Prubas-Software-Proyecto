/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Funcionalidad;

import Objetos.Interfaces.IObjetoNegocioEncriptador;
import Objetos.ObjetoNegocioEncriptador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author tacot
 */
public class EncriptadorSSTest {
    IObjetoNegocioEncriptador enc;
    
    @BeforeEach
    public void setUp() {
        enc = new ObjetoNegocioEncriptador();
    }

    /**
     * Test of getAES method, of class EncriptadorSS.
     */
    @Test
    public void testGetAES() {
        //arrange
        System.out.println("testGetAES");
        
        String expResult = "6BDAH9Weo0SuDet9yzRp/Q==";
        
        //act
        String result = enc.getAES("ejemplo");
        
        //assert
        assertEquals(expResult, result);
        
    }
    
}
