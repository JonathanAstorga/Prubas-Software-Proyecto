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
public class DesencriptadorSSTest {
    IObjetoNegocioEncriptador enc;
    
    @BeforeEach
    public void setUp() {
        enc = new ObjetoNegocioEncriptador();
    }
    /**
     * Test of getAESDecrypt method, of class DesencriptadorSS.
     */
    @Test
    public void testGetAESDecrypt() {
        
        //arrange
        System.out.println("testGetAESDecrypt");
        
        String expResult = "ejemplo";
        
        //act
        String result = enc.getAESDecrypt("6BDAH9Weo0SuDet9yzRp/Q==");
        
        //assert
        assertEquals(expResult, result);
        
    }
    
}
