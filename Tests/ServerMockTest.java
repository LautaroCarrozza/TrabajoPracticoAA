import org.junit.Test;

import static org.junit.Assert.*;

public class ServerMockTest {

    @Test
    public void testBuscadorDeVuelo(){
        ServerInterface server = new ServerMock();
        server.setUpTest();
        System.out.println(server.buscarVuelos(10,10,2017,"Buenos Aires", "Montevideo", 1, "Economy"));
        System.out.println(server.getVuelo(101).asientosDisponibles());
    }
    @Test (expected = RuntimeException.class)
    public void asientoNoSeVendeDosVeces(){
        ServerInterface server = new ServerMock();
        server.setUpTest();
        server.comprarPasaje(101, 123, 3, 'B');
        server.comprarPasaje(101, 124, 3, 'B');

    }
    @Test
    public void test02 (){
        String string = "";
        assertEquals(true, string.isEmpty());

    }
}