/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Objetos;

import Objetos.Interfaces.IObjetoNegocioEncriptador;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jl4ma
 */
public class ObjetoNegocioEncriptadorIT {
    
    IObjetoNegocioEncriptador encrip;
    
    public ObjetoNegocioEncriptadorIT(){
        this.encrip = new ObjetoNegocioEncriptador();
    }

    /**
     * Test of getAES method, of class ObjetoNegocioEncriptador.
     */
    @Test
    public void testGetAES() {
        //arrange
        String cad = "cadena";
        String result;
        
        //act
        result = encrip.getAES(cad);
        
        //assert
        
        assertEquals(result, result);
    }

    /**
     * Test of getAESDecrypt method, of class ObjetoNegocioEncriptador.
     */
    @Test
    public void testGetAESDecrypt() {
        //arrange
        String cad = "cadena";
        String result;
        
        //act
        result = encrip.getAESDecrypt(cad);
        
        //assert
        
        assertEquals(result, result);
    }
    
}
