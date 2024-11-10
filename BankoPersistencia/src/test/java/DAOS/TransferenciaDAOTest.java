/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAOS;

import Conexion.Conexion;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import entidades.Persona;
import entidades.Tarjeta;
import entidades.Transferencia;
import interfaces.daos.ITarjetaDAO;
import java.util.ArrayList;
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

    @Mock
    FindIterable findIterable;

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
    public void testObtenerTransferenciasEgreso_List_ReturnSuccess() {
        // Arrange
        System.out.println("obtenerTransferenciasEgreso");
        Tarjeta tarjeta = new Tarjeta("10");
        Date fechaInicio = new Date();
        Date fechaFin = new Date();
        Transferencia transferencia1 = new Transferencia("1", tarjeta.getNumeroCuenta(),
                20D, "Nomas",
                fechaInicio);
        Transferencia transferencia2 = new Transferencia("2", tarjeta.getNumeroCuenta(),
                20D, "Te lo devuelvo",
                fechaInicio);
        List<Transferencia> listaEsperada = new ArrayList<>();
        listaEsperada.add(transferencia1);
        listaEsperada.add(transferencia2);

        Tarjeta tarjetaEspia = Mockito.spy(tarjeta);

        Mockito.when(transferenciaMock.find(Mockito.any(Bson.class)))
                .thenReturn(findIterable);

        Mockito.when(findIterable.into(Mockito.any(List.class)))
                .thenReturn(listaEsperada);

        // Act
        List<Transferencia> resultado = transferenciaDao.obtenerTransferenciasEgreso(tarjetaEspia, fechaInicio, fechaFin);

        // Assert
        assertEquals(listaEsperada, resultado);
        Mockito.verify(tarjetaEspia, Mockito.times(2)).getNumeroCuenta();
        Mockito.verify(tarjetaEspia, Mockito.times(1)).
                setNumeroCuenta(Mockito.any(String.class));

    }

    /**
     * Test of obtenerTransferenciasIngreso method, of class TransferenciaDAO.
     */
    @Test // #20
    public void testObtenerTransferenciasIngreso_List_ReturnSuccess() {
        // Arrange
        System.out.println("obtenerTransferenciasIngreso");
        Tarjeta tarjeta = new Tarjeta("9");
        Date fechaInicio = new Date();
        Date fechaFin = new Date();
        Transferencia transferencia1 = new Transferencia(tarjeta.getNumeroCuenta(), "20", 20D, "Nomas",
                fechaInicio);
        Transferencia transferencia2 = new Transferencia(tarjeta.getNumeroCuenta(), "10", 20D, "Te lo devuelvo",
                fechaInicio);
        List<Transferencia> listaEsperada = new ArrayList<>();
        listaEsperada.add(transferencia1);
        listaEsperada.add(transferencia2);

        Tarjeta tarjetaEspia = Mockito.spy(tarjeta);

        Mockito.when(transferenciaMock.find(Mockito.any(Bson.class)))
                .thenReturn(findIterable);

        Mockito.when(findIterable.into(Mockito.any(List.class)))
                .thenReturn(listaEsperada);

        // Act
        List<Transferencia> resultado = transferenciaDao.obtenerTransferenciasEgreso(tarjetaEspia, fechaInicio, fechaFin);

        // Assert
        assertEquals(listaEsperada, resultado);
        Mockito.verify(tarjetaEspia, Mockito.times(2)).getNumeroCuenta();
        Mockito.verify(tarjetaEspia, Mockito.times(1)).
                setNumeroCuenta(Mockito.any(String.class));
    }

    /**
     * Test of obtenerTransferencias method, of class TransferenciaDAO.
     */
    @Test // #21
    public void testObtenerTransferencias_List_ReturnSuccess() {
        // Arrange
        System.out.println("obtenerTransferencias");
        
        TransferenciaDAO transferenciaDAOMock = Mockito.mock(TransferenciaDAO.class);
        
        Tarjeta tarjeta = new Tarjeta("800");
        
        Date fechaInicio = new Date();
        Date fechaFin = new Date();
        
        Transferencia transferencia1 = new Transferencia(tarjeta.getNumeroCuenta(), "20", 20D, "Nomas",
                fechaInicio);
        Transferencia transferencia2 = new Transferencia("10", tarjeta.getNumeroCuenta(), 10D, "Te lo devuelvo",
                fechaInicio);

        List<Transferencia> listaIngresos = new ArrayList<>();
        listaIngresos.add(transferencia1);

        List<Transferencia> listaEgresos = new ArrayList<>();
        listaEgresos.add(transferencia2);

        List<Transferencia> listaEsperada = new ArrayList<>();
        listaEsperada.addAll(listaIngresos);
        listaEsperada.addAll(listaEgresos);

         Mockito.when(transferenciaDAOMock.obtenerTransferenciasIngreso(
                 Mockito.any(Tarjeta.class), 
                 Mockito.any(Date.class), 
                 Mockito.any(Date.class)))
                 .thenReturn(listaIngresos);
         
         Mockito.when(transferenciaDAOMock.obtenerTransferenciasEgreso(
                 Mockito.any(Tarjeta.class), 
                 Mockito.any(Date.class), 
                 Mockito.any(Date.class)))
                 .thenReturn(listaEgresos);
         
         Mockito.when(transferenciaDAOMock.obtenerTransferencias(
                 Mockito.any(Tarjeta.class), 
                 Mockito.any(Date.class), 
                 Mockito.any(Date.class)))
                 .thenCallRealMethod();

        // Act
        List<Transferencia> resultado = transferenciaDAOMock.obtenerTransferencias(tarjeta, fechaInicio, fechaFin);

        // Assert
        assertEquals(listaEsperada, resultado);
        
        Mockito.verify(transferenciaDAOMock, Mockito.times(1))
                .obtenerTransferenciasEgreso(tarjeta, 
                 fechaInicio, 
                 fechaFin);
        
        Mockito.verify(transferenciaDAOMock, Mockito.times(1))
                .obtenerTransferenciasIngreso(tarjeta, 
                 fechaInicio, 
                 fechaFin);
    }

    /**
     * Test of obtenerTransferenciasSinFecha method, of class TransferenciaDAO.
     */
    @Test // #22
    public void testObtenerTransferenciasSinFecha_List_ReturnSuccess() {
        // Arrange
        System.out.println("testObtenerTransferenciasSinFecha");
        
        Tarjeta tarjeta = new Tarjeta("2006");
        Tarjeta tarjetaEspia = Mockito.spy(tarjeta);
        
        Transferencia transferencia1 = new Transferencia(tarjeta.getNumeroCuenta(), "20", 20D, "Nomas",
                new Date());

        List<Transferencia> listaEsperada = new ArrayList<>();
        listaEsperada.add(transferencia1);
        
        Mockito.when(transferenciaMock.find(Mockito.any(Bson.class)))
                .thenReturn(findIterable);
        
        Mockito.when(findIterable.into(Mockito.any(List.class)))
                .thenReturn(listaEsperada);
        
        // Act
        List<Transferencia> resultado = transferenciaDao.obtenerTransferenciasSinFecha(tarjetaEspia);
        
        // Assert
        assertEquals(listaEsperada, resultado);
        
        Mockito.verify(tarjetaEspia, Mockito.times(1))
                .setNumeroCuenta(Mockito.any(String.class));
        
        Mockito.verify(tarjetaEspia, Mockito.times(3))
                .getNumeroCuenta();
    }

    /**
     * Test of ingresosDelDia method, of class TransferenciaDAO.
     */
    @Test // #23
    public void testIngresosDelDia_Boolean_ReturnSuccess() {
        // Arrange
        System.out.println("ingresosDelDia");
        
        TransferenciaDAO transferenciaDAOMock = Mockito.mock(TransferenciaDAO.class);
        
        Tarjeta tarjeta = new Tarjeta("302");
        
        Transferencia transferencia1 = new Transferencia(tarjeta.getNumeroCuenta(), "20", 20D, "Nomas",
                new Date());
        Transferencia transferencia2 = new Transferencia(tarjeta.getNumeroCuenta(), "20", 10D, "Nomas x2",
                new Date());

        List<Transferencia> listaIngresosDia = new ArrayList<>();
        listaIngresosDia.add(transferencia1);
        listaIngresosDia.add(transferencia2);
        
        Double esperado = 30D;
        
        Mockito.when(transferenciaDAOMock.ingresosDelDia(Mockito.any(Tarjeta.class)))
                .thenCallRealMethod();
        
        Mockito.when(transferenciaDAOMock.obtenerTransferenciasIngreso(
                Mockito.any(Tarjeta.class), 
                Mockito.any(Date.class), 
                Mockito.any(Date.class)))
                .thenReturn(listaIngresosDia);
        
        // Act
        Double resultado = transferenciaDAOMock.ingresosDelDia(tarjeta);
        
        // Assert
        assertEquals(esperado, resultado);
    }
    
    @Test // #24
    public void testIngresosDelDia_SinIngresos_ReturnSuccess() {
        // Arrange
        System.out.println("ingresosDelDia Caso listaIngresos null");
        
        TransferenciaDAO transferenciaDAOMock = Mockito.mock(TransferenciaDAO.class);
        
        Tarjeta tarjeta = new Tarjeta("302");
        
        Double esperado = 0D;
        
        Mockito.when(transferenciaDAOMock.ingresosDelDia(Mockito.any(Tarjeta.class)))
                .thenCallRealMethod();
        
        Mockito.when(transferenciaDAOMock.obtenerTransferenciasIngreso(
                Mockito.any(Tarjeta.class), 
                Mockito.any(Date.class), 
                Mockito.any(Date.class)))
                .thenReturn(null);
        
        // Act
        Double resultado = transferenciaDAOMock.ingresosDelDia(tarjeta);
        
        // Assert
        assertEquals(esperado, resultado);
    }

    /**
     * Test of egresosDelDia method, of class TransferenciaDAO.
     */
    @Test // #25
    public void testEgresosDelDia_Boolean_ReturnSuccess() {
        // Arrange
        System.out.println("egresosDelDia");
        
        TransferenciaDAO transferenciaDAOMock = Mockito.mock(TransferenciaDAO.class);
        
        Tarjeta tarjeta = new Tarjeta("301");
        
        Transferencia transferencia1 = new Transferencia( "20", tarjeta.getNumeroCuenta(), 200D, "Nomas",
                new Date());
        Transferencia transferencia2 = new Transferencia( "20", tarjeta.getNumeroCuenta(), 10D, "Nomas x2",
                new Date());

        List<Transferencia> listaEgresoDia = new ArrayList<>();
        listaEgresoDia.add(transferencia1);
        listaEgresoDia.add(transferencia2);
        
        Double esperado = 210D;
        
        Mockito.when(transferenciaDAOMock.egresosDelDia(Mockito.any(Tarjeta.class)))
                .thenCallRealMethod();
        
        Mockito.when(transferenciaDAOMock.obtenerTransferenciasEgreso(
                Mockito.any(Tarjeta.class), 
                Mockito.any(Date.class), 
                Mockito.any(Date.class)))
                .thenReturn(listaEgresoDia);
        
        // Act
        Double resultado = transferenciaDAOMock.egresosDelDia(tarjeta);
        
        // Assert
        assertEquals(esperado, resultado);
    }
    
    @Test // #26
    public void testEgresosDelDia_SinEgresos_ReturnSuccess() {
        // Arrange
        System.out.println("egresosDelDia Caso listaEgresos null");
        
        TransferenciaDAO transferenciaDAOMock = Mockito.mock(TransferenciaDAO.class);
        
        Tarjeta tarjeta = new Tarjeta("302");
        
        Double esperado = 0D;
        
        Mockito.when(transferenciaDAOMock.egresosDelDia(Mockito.any(Tarjeta.class)))
                .thenCallRealMethod();
        
        Mockito.when(transferenciaDAOMock.obtenerTransferenciasEgreso(
                Mockito.any(Tarjeta.class), 
                Mockito.any(Date.class), 
                Mockito.any(Date.class)))
                .thenReturn(null);
        
        // Act
        Double resultado = transferenciaDAOMock.egresosDelDia(tarjeta);
        
        // Assert
        assertEquals(esperado, resultado);
    }

}
