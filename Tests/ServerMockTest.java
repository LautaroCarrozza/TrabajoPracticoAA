import org.junit.Test;

import static org.junit.Assert.*;

public class ServerMockTest {

    @Test
    public void test01(){
        ServerMock server = new ServerMock();
        server.addAeropuerto("AEP", "Buenos Aires", "Aeroparque");
        System.out.println(server.aeropuertos);
    }

}