import org.junit.Test;

import static org.junit.Assert.*;

public class ServerMockTest {

    @Test
    public void test01(){
        ServerMock server = new ServerMock();
        server.addAeropuerto("AEP", "Buenos Aires", "Aeroparque");
        System.out.println(Aeropuerto.build(server.aeropuertosSaver.get()));
        server.addCliente(123, "lauti", 3);
        System.out.println(Cliente.build(server.clientesSaver.get()));
        server.addVuelo("AEP", "AEP", 1, 1, 2018, 22, 30, "1", 1);
        System.out.println(Vuelo.build( server.vuelosSaver.get()) );
    }

}