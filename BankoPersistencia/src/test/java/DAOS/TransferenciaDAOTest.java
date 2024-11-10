/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAOS;

import Conexion.Conexion;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import entidades.Persona;
import entidades.Tarjeta;
import entidades.Transferencia;
import interfaces.daos.ITarjetaDAO;
import java.util.Date;
import java.util.List;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Dell
 */
public class TransferenciaDAOTest {

    @Mock
    MongoCollection transferenciaMock;
    
    @Mock
    MongoCollection personasMock;
    
    @Mock
    TarjetaDAO tarjetaMock;
    
    @Mock
    UpdateResult updateResult;
    
    @Mock
    InsertOneResult insertOneResult;

    @InjectMocks
    TransferenciaDAO transferenciaDao;

    public TransferenciaDAOTest() {
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test of realizarTransferencia method, of class TransferenciaDAO.
     */
    @Test // #13
    public void testRealizarTransferencia_Boolean_ReturnSuccess() {
        // Arrange
        System.out.println("testRealizarTransferencia()");
        
            // Creación de la transferencia
        Transferencia transferencia = new Transferencia();
        transferencia.setNumeroCuentaPropietario("1");
        transferencia.setNumeroCuentaDestinatario("2");
        transferencia.setImporte(50D);

            // Creacion de las tarjetas propietario y destinatario
        Tarjeta tarjetaPropietario = new Tarjeta("1");
        Tarjeta tarjetaDestinatario = new Tarjeta("2");
        tarjetaPropietario.setSaldo(100D);
        tarjetaDestinatario.setSaldo(0D);

            // Creación de las personas destinatario y propietario
        Persona personaPropietario = new Persona("01");
        Persona personaDestinatario = new Persona("02");
        
            // Creación de una trasferencia espia para verificar que se usen los metodos de esta clase para guardarla
            // Esto clona la transferencia original y hace que el Mockito pueda espiar lo que hace la clase transferencia dentro de este metodo
        Transferencia transferenciaEspia = Mockito.spy(transferencia);

            // Al momento de que la tarjetaMock llame al metodo obtenerTarjetaPorNumero devuelve una tarjeta sin consultar nada
        Mockito.when(tarjetaMock.obtenerTarjetaPorNumero(Mockito.any(Tarjeta.class)))
                .thenReturn(tarjetaPropietario) // Primera llamada devuelve tarjetaPropietario
                .thenReturn(tarjetaDestinatario); // Segunda llamada devuelvve tarjetaDestinatario

            // Al momento de que la tarjetaMock llame al metodo obtenerPersonaDeTarjeta devuelve a una persona sin consultar nada
        Mockito.when(tarjetaMock.obtenerPersonaDeTarjeta(tarjetaPropietario))
                .thenReturn(personaPropietario); // Devuelve personaPropietario
        
            // Al momento de que la tarjetaMock llame al metodo obtenerPersonaDeTarjeta devuelve a una persona sin consultar nada
        Mockito.when(tarjetaMock.obtenerPersonaDeTarjeta(tarjetaDestinatario))
                .thenReturn(personaDestinatario); //Devuelve personaDestinatario

            // Cuando personasMock pide que se haga un update en la base de datos no busca nada y solo devuelve un resultado
        Mockito.when(personasMock.updateOne(Mockito.any(Bson.class), Mockito.any(Bson.class)))
                .thenReturn(updateResult); // Devuelve updateResult

            // Cuando transferenciaMock pide que se haga un insert en la base de datos no busca nada y solo devuelve un resultado
        Mockito.when(transferenciaMock.insertOne(Mockito.any(Transferencia.class)))
                .thenReturn(insertOneResult); // Devuelve insertOneResult

        // Act
        boolean resultado = transferenciaDao.realizarTransferencia(transferenciaEspia);

        // Assert
        assertTrue(resultado); // Valida que el metodo devolvio un true lo que quiere decir que se completo con exito
        
        Mockito.verify(tarjetaMock, Mockito.times(2)) 
                .obtenerTarjetaPorNumero(Mockito.any(Tarjeta.class)); // Verifica que tarjetaMock llame al metodo obtenerTarjetaPorNumero dos veces
        Mockito.verify(tarjetaMock, Mockito.times(2))
                .obtenerPersonaDeTarjeta(Mockito.any(Tarjeta.class)); // Verifica que tarjetaMock llame al metodo obtenerPersonaDeTarjeta dos veces
        
        Mockito.verify(transferenciaEspia, Mockito.times(1))
                .setFechaMovimiento(Mockito.any(Date.class)); // Verifica que trasnferenciaEspia llame al metodo setFechaMovimiento una vez
        
        Mockito.verify(transferenciaEspia, Mockito.times(1))
                .setNumeroCuentaPropietario(Mockito.any(String.class)); // Verifica que trasnferenciaEspia llame al metodo setNumeroCuentaPropietario una vez
        
        Mockito.verify(transferenciaEspia, Mockito.times(1))
                .setNumeroCuentaDestinatario(Mockito.any(String.class)); // Verifica que trasnferenciaEspia llame al metodo setNumeroCuentaDestinatario una vez
    }
    
    @Test // #14
    public void testRealizarTransferencia_ErrorPropietarioYDestinatarioIguales_ReturnSuccess() {
        // Arrange
        System.out.println("testRealizarTransferencia() Error en if #1");
        
            // Creación de la transferencia
        Transferencia transferencia = new Transferencia();
        transferencia.setNumeroCuentaPropietario("1");
        transferencia.setNumeroCuentaDestinatario("1");
        transferencia.setImporte(50D);
        
        // Creación de una trasferencia espia para verificar que se usen los metodos de esta clase para guardarla
            // Esto clona la transferencia original y hace que el Mockito pueda espiar lo que hace la clase transferencia dentro de este metodo
        Transferencia transferenciaEspia = Mockito.spy(transferencia);
        
        // Act
        boolean resultado = transferenciaDao.realizarTransferencia(transferenciaEspia);

        // Assert
        assertFalse(resultado); // Valida que el metodo devolvio un false lo que quiere decir que hubo un fallo

        Mockito.verify(transferenciaEspia, Mockito.times(1))
                .getNumeroCuentaDestinatario(); // Verifica que trasnferenciaEspia llame al metodo getNumeroCuentaDestinatario una vez
        Mockito.verify(transferenciaEspia, Mockito.times(1))
                .getNumeroCuentaPropietario(); // Verifica que trasnferenciaEspia llame al metodo getNumeroCuentaPropietario una vez
    }
    
    @Test // #15
    public void testRealizarTransferencia_ErrorPersonaDestinatarioNulo_ReturnSuccess() {
        // Arrange
        System.out.println("testRealizarTransferencia() Error en if #2");
        
            // Creación de la transferencia
        Transferencia transferencia = new Transferencia();
        transferencia.setNumeroCuentaPropietario("1");
        transferencia.setNumeroCuentaDestinatario("2");
        transferencia.setImporte(50D);

            // Creacion de las tarjetas propietario y destinatario
        Tarjeta tarjetaPropietario = new Tarjeta("1");
        Tarjeta tarjetaDestinatario = new Tarjeta("2");
        tarjetaPropietario.setSaldo(100D);
        tarjetaDestinatario.setSaldo(0D);

            // Creación de las personas destinatario y propietario
        Persona personaPropietario = new Persona("01");
        Persona personaDestinatario = new Persona("02");
        
            // Creación de una trasferencia espia para verificar que se usen los metodos de esta clase para guardarla
            // Esto clona la transferencia original y hace que el Mockito pueda espiar lo que hace la clase transferencia dentro de este metodo
        Transferencia transferenciaEspia = Mockito.spy(transferencia);

            // Al momento de que la tarjetaMock llame al metodo obtenerTarjetaPorNumero devuelve una tarjeta sin consultar nada
        Mockito.when(tarjetaMock.obtenerTarjetaPorNumero(Mockito.any(Tarjeta.class)))
                .thenReturn(tarjetaPropietario) // Primera llamada devuelve tarjetaPropietario
                .thenReturn(tarjetaDestinatario); // Segunda llamada devuelvve tarjetaDestinatario

            // Al momento de que la tarjetaMock llame al metodo obtenerPersonaDeTarjeta devuelve a una persona sin consultar nada
        Mockito.when(tarjetaMock.obtenerPersonaDeTarjeta(tarjetaPropietario))
                .thenReturn(personaPropietario); // Devuelve personaPropietario
        
            // Al momento de que la tarjetaMock llame al metodo obtenerPersonaDeTarjeta devuelve a una persona sin consultar nada
        Mockito.when(tarjetaMock.obtenerPersonaDeTarjeta(tarjetaDestinatario))
                .thenReturn(null); //Devuelve personaDestinatario
        
        // Act
        boolean resultado = transferenciaDao.realizarTransferencia(transferenciaEspia);

        // Assert
        assertFalse(resultado); // Valida que el metodo devolvio un false lo que quiere decir que hubo un fallo

        Mockito.verify(tarjetaMock, Mockito.times(2)) 
                .obtenerTarjetaPorNumero(Mockito.any(Tarjeta.class)); // Verifica que tarjetaMock llame al metodo obtenerTarjetaPorNumero dos veces
        Mockito.verify(tarjetaMock, Mockito.times(2))
                .obtenerPersonaDeTarjeta(Mockito.any(Tarjeta.class)); // Verifica que tarjetaMock llame al metodo obtenerPersonaDeTarjeta dos veces
    }
    
    @Test // #16
    public void testRealizarTransferencia_ErrorPersonaPropietarioNulo_ReturnSuccess() {
        // Arrange
        System.out.println("testRealizarTransferencia() Error en if #2");
        
            // Creación de la transferencia
        Transferencia transferencia = new Transferencia();
        transferencia.setNumeroCuentaPropietario("1");
        transferencia.setNumeroCuentaDestinatario("2");
        transferencia.setImporte(50D);

            // Creacion de las tarjetas propietario y destinatario
        Tarjeta tarjetaPropietario = new Tarjeta("1");
        Tarjeta tarjetaDestinatario = new Tarjeta("2");
        tarjetaPropietario.setSaldo(100D);
        tarjetaDestinatario.setSaldo(0D);

            // Creación de las personas destinatario y propietario
        Persona personaPropietario = new Persona("01");
        Persona personaDestinatario = new Persona("02");
        
            // Creación de una trasferencia espia para verificar que se usen los metodos de esta clase para guardarla
            // Esto clona la transferencia original y hace que el Mockito pueda espiar lo que hace la clase transferencia dentro de este metodo
        Transferencia transferenciaEspia = Mockito.spy(transferencia);

            // Al momento de que la tarjetaMock llame al metodo obtenerTarjetaPorNumero devuelve una tarjeta sin consultar nada
        Mockito.when(tarjetaMock.obtenerTarjetaPorNumero(Mockito.any(Tarjeta.class)))
                .thenReturn(tarjetaPropietario) // Primera llamada devuelve tarjetaPropietario
                .thenReturn(tarjetaDestinatario); // Segunda llamada devuelvve tarjetaDestinatario

            // Al momento de que la tarjetaMock llame al metodo obtenerPersonaDeTarjeta devuelve a una persona sin consultar nada
        Mockito.when(tarjetaMock.obtenerPersonaDeTarjeta(tarjetaPropietario))
                .thenReturn(null); // Devuelve personaPropietario
        
            // Al momento de que la tarjetaMock llame al metodo obtenerPersonaDeTarjeta devuelve a una persona sin consultar nada
        Mockito.when(tarjetaMock.obtenerPersonaDeTarjeta(tarjetaDestinatario))
                .thenReturn(personaDestinatario); //Devuelve personaDestinatario
        
        // Act
        boolean resultado = transferenciaDao.realizarTransferencia(transferenciaEspia);

        // Assert
        assertFalse(resultado); // Valida que el metodo devolvio un false lo que quiere decir que hubo un fallo

        Mockito.verify(tarjetaMock, Mockito.times(2)) 
                .obtenerTarjetaPorNumero(Mockito.any(Tarjeta.class)); // Verifica que tarjetaMock llame al metodo obtenerTarjetaPorNumero dos veces
        Mockito.verify(tarjetaMock, Mockito.times(2))
                .obtenerPersonaDeTarjeta(Mockito.any(Tarjeta.class)); // Verifica que tarjetaMock llame al metodo obtenerPersonaDeTarjeta dos veces
    }
    
    @Test // #17
    public void testRealizarTransferencia_ErrorTarjetaPropietarioNula_ReturnSuccess() {
        // Arrange
        System.out.println("testRealizarTransferencia() Error en if #3");
        
            // Creación de la transferencia
        Transferencia transferencia = new Transferencia();
        transferencia.setNumeroCuentaPropietario("1");
        transferencia.setNumeroCuentaDestinatario("2");
        transferencia.setImporte(50D);

            // Creacion de las tarjetas propietario y destinatario
        Tarjeta tarjetaPropietario = new Tarjeta("1");
        Tarjeta tarjetaDestinatario = new Tarjeta("2");
        tarjetaPropietario.setSaldo(100D);
        tarjetaDestinatario.setSaldo(0D);

            // Creación de las personas destinatario y propietario
        Persona personaPropietario = new Persona("01");
        Persona personaDestinatario = new Persona("02");
        
            // Creación de una trasferencia espia para verificar que se usen los metodos de esta clase para guardarla
            // Esto clona la transferencia original y hace que el Mockito pueda espiar lo que hace la clase transferencia dentro de este metodo
        Transferencia transferenciaEspia = Mockito.spy(transferencia);

            // Al momento de que la tarjetaMock llame al metodo obtenerTarjetaPorNumero devuelve una tarjeta sin consultar nada
        Mockito.when(tarjetaMock.obtenerTarjetaPorNumero(Mockito.any(Tarjeta.class)))
                .thenReturn(null) // Primera llamada devuelve tarjetaPropietario
                .thenReturn(tarjetaDestinatario); // Segunda llamada devuelvve tarjetaDestinatario

            // Al momento de que la tarjetaMock llame al metodo obtenerPersonaDeTarjeta devuelve a una persona sin consultar nada
        Mockito.when(tarjetaMock.obtenerPersonaDeTarjeta(tarjetaPropietario))
                .thenReturn(personaPropietario); // Devuelve personaPropietario
        
            // Al momento de que la tarjetaMock llame al metodo obtenerPersonaDeTarjeta devuelve a una persona sin consultar nada
        Mockito.when(tarjetaMock.obtenerPersonaDeTarjeta(tarjetaDestinatario))
                .thenReturn(personaDestinatario); //Devuelve personaDestinatario
        
        // Act
        boolean resultado = transferenciaDao.realizarTransferencia(transferenciaEspia);

        // Assert
        assertFalse(resultado); // Valida que el metodo devolvio un false lo que quiere decir que hubo un fallo

        Mockito.verify(tarjetaMock, Mockito.times(2)) 
                .obtenerTarjetaPorNumero(Mockito.any(Tarjeta.class)); // Verifica que tarjetaMock llame al metodo obtenerTarjetaPorNumero dos veces
        Mockito.verify(tarjetaMock, Mockito.times(1))
                .obtenerPersonaDeTarjeta(Mockito.any(Tarjeta.class)); // Verifica que tarjetaMock llame al metodo obtenerPersonaDeTarjeta dos veces
    }
    
    @Test // #18
    public void testRealizarTransferencia_ErrorSaldoInsuficiente_ReturnSuccess() {
        // Arrange
        System.out.println("testRealizarTransferencia() Error en if #3");
        
            // Creación de la transferencia
        Transferencia transferencia = new Transferencia();
        transferencia.setNumeroCuentaPropietario("1");
        transferencia.setNumeroCuentaDestinatario("2");
        transferencia.setImporte(50D);

            // Creacion de las tarjetas propietario y destinatario
        Tarjeta tarjetaPropietario = new Tarjeta("1");
        Tarjeta tarjetaDestinatario = new Tarjeta("2");
        tarjetaPropietario.setSaldo(10D);
        tarjetaDestinatario.setSaldo(0D);

            // Creación de las personas destinatario y propietario
        Persona personaPropietario = new Persona("01");
        Persona personaDestinatario = new Persona("02");
        
            // Creación de una trasferencia espia para verificar que se usen los metodos de esta clase para guardarla
            // Esto clona la transferencia original y hace que el Mockito pueda espiar lo que hace la clase transferencia dentro de este metodo
        Transferencia transferenciaEspia = Mockito.spy(transferencia);

            // Al momento de que la tarjetaMock llame al metodo obtenerTarjetaPorNumero devuelve una tarjeta sin consultar nada
        Mockito.when(tarjetaMock.obtenerTarjetaPorNumero(Mockito.any(Tarjeta.class)))
                .thenReturn(tarjetaPropietario) // Primera llamada devuelve tarjetaPropietario
                .thenReturn(tarjetaDestinatario); // Segunda llamada devuelvve tarjetaDestinatario

            // Al momento de que la tarjetaMock llame al metodo obtenerPersonaDeTarjeta devuelve a una persona sin consultar nada
        Mockito.when(tarjetaMock.obtenerPersonaDeTarjeta(tarjetaPropietario))
                .thenReturn(personaPropietario); // Devuelve personaPropietario
        
            // Al momento de que la tarjetaMock llame al metodo obtenerPersonaDeTarjeta devuelve a una persona sin consultar nada
        Mockito.when(tarjetaMock.obtenerPersonaDeTarjeta(tarjetaDestinatario))
                .thenReturn(personaDestinatario); //Devuelve personaDestinatario
        
        // Act
        boolean resultado = transferenciaDao.realizarTransferencia(transferenciaEspia);

        // Assert
        assertFalse(resultado); // Valida que el metodo devolvio un false lo que quiere decir que hubo un fallo

        Mockito.verify(tarjetaMock, Mockito.times(2)) 
                .obtenerTarjetaPorNumero(Mockito.any(Tarjeta.class)); // Verifica que tarjetaMock llame al metodo obtenerTarjetaPorNumero dos veces
        Mockito.verify(tarjetaMock, Mockito.times(2))
                .obtenerPersonaDeTarjeta(Mockito.any(Tarjeta.class)); // Verifica que tarjetaMock llame al metodo obtenerPersonaDeTarjeta dos veces
    }

    /**
     * Test of obtenerTransferenciasEgreso method, of class TransferenciaDAO.
     */
    @Test // #19
    public void testObtenerTransferenciasEgreso() {
        System.out.println("obtenerTransferenciasEgreso");
        Tarjeta tarjeta = null;
        Date fechaInicio = null;
        Date fechaFin = null;
        TransferenciaDAO instance = new TransferenciaDAO();
        List<Transferencia> expResult = null;
        List<Transferencia> result = instance.obtenerTransferenciasEgreso(tarjeta, fechaInicio, fechaFin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerTransferenciasIngreso method, of class TransferenciaDAO.
     */
    @Test // #20
    public void testObtenerTransferenciasIngreso() {
        System.out.println("obtenerTransferenciasIngreso");
        Tarjeta tarjeta = null;
        Date fechaInicio = null;
        Date fechaFin = null;
        TransferenciaDAO instance = new TransferenciaDAO();
        List<Transferencia> expResult = null;
        List<Transferencia> result = instance.obtenerTransferenciasIngreso(tarjeta, fechaInicio, fechaFin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerTransferencias method, of class TransferenciaDAO.
     */
    @Test // #21
    public void testObtenerTransferencias() {
        System.out.println("obtenerTransferencias");
        Tarjeta tarjeta = null;
        Date fechaInicio = null;
        Date fechaFin = null;
        TransferenciaDAO instance = new TransferenciaDAO();
        List<Transferencia> expResult = null;
        List<Transferencia> result = instance.obtenerTransferencias(tarjeta, fechaInicio, fechaFin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerTransferenciasSinFecha method, of class TransferenciaDAO.
     */
    @Test // #22
    public void testObtenerTransferenciasSinFecha() {
        System.out.println("obtenerTransferenciasSinFecha");
        Tarjeta tarjeta = null;
        TransferenciaDAO instance = new TransferenciaDAO();
        List<Transferencia> expResult = null;
        List<Transferencia> result = instance.obtenerTransferenciasSinFecha(tarjeta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ingresosDelDia method, of class TransferenciaDAO.
     */
    @Test // #23
    public void testIngresosDelDia() {
        System.out.println("ingresosDelDia");
        Tarjeta tarjeta = null;
        TransferenciaDAO instance = new TransferenciaDAO();
        Double expResult = null;
        Double result = instance.ingresosDelDia(tarjeta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of egresosDelDia method, of class TransferenciaDAO.
     */
    @Test // #24
    public void testEgresosDelDia() {
        System.out.println("egresosDelDia");
        Tarjeta tarjeta = null;
        TransferenciaDAO instance = new TransferenciaDAO();
        Double expResult = null;
        Double result = instance.egresosDelDia(tarjeta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
