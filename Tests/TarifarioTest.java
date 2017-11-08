import org.junit.Test;

import static org.junit.Assert.*;

public class TarifarioTest {

    @Test
    public void test01AgregarTarifaPedirlaBorrarlaYPedirla(){
        ServerInterface server = new ServerMock();
        server.setUp();
        Tarifario tarifario = new Tarifario();

       tarifario.addTarifa("Economy", 101, 100);
       assertEquals(100, tarifario.getPreciodeTarifa("101-Economy"));
    }

}