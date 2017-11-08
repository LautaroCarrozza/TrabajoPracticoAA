import org.junit.Test;

public class ServerMockTest {

    @Test
    public void test01(){
        ServerMock server = new ServerMock();

        server.addCliente(123, "lauti", 3);
        server.addAeropuerto("aaa", "aaa", "aaa");
        server.addTipoDeAvion(1, 1, 1, 1, 1, 1, 1, "a");


        System.out.println(server.getCliente(3));

    }



}