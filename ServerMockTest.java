import org.junit.Test;

import static org.junit.Assert.*;

public class ServerMockTest {

    @Test
    public void testBuscadorDeVuelo(){
        ServerInterface server = new ServerMock();
        server.setUpTest();
        System.out.println(server.buscarVuelo(10,10,2017,"Buenos Aires", "Montevideo", 1, "Economy"));
        System.out.println(server.getVuelo("001").asientosDisponibles());
    }
}