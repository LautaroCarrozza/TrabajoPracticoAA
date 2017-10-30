import org.junit.Test;

import static org.junit.Assert.*;

public class ServerMockTest {

    @Test
    public void testBuscadorDeVuelo(){

        ServerInterface server = new ServerMock();
        server.setUpTest();

        System.out.println(server.buscarVuelos(1,1,2017,"aaa", "bbb", 1, "Economy"));
    }

    @Test
    public void testAsientosDisponibles(){
        ServerInterface server = new ServerMock();
        server.setUpTest();

        System.out.println(server.getVuelo(1).cantidadAsientosDisponibles("Economy"));

    }

    @Test
    public void test02 (){
        String string = "";
        assertEquals(true, string.isEmpty());

    }
}