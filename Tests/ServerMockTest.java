import org.junit.Test;

public class ServerMockTest {

    @Test
    public void test01(){
        ServerMock server = new ServerMock();
        server.setUp();

        server.addCliente(123, "lauti", 3);
        server.addEmpleado(123, "marian", 1, true);
        server.addPersonalAbordo(123, "juani", "piloto", 1);
        server.addAeropuerto("aaa", "aaa", "aaa");
        server.addTipoDeAvion(1, 1, 1, 1, 1, 1, 1, "a");
        server.addAvion("1", "a");
        server.addVuelo("aaa", "aaa", 1, 1, 2018, 22, 30, "1", 1, 3);




    }



}