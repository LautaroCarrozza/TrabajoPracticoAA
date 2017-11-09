import org.junit.Test;

@SuppressWarnings("ALL")
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
        server.addVuelo("aaa", "aaa", 1, 1, 2018, 22, 30,60, "1", 1, 3);

    }

    @Test
    public void test02(){
        ServerMock server = new ServerMock();
        server.setUp();

        server.addCliente(123, "lauti", 3);
        server.addEmpleado(123, "marian", 1, true);
        server.addPersonalAbordo(123, "juani", "piloto", 1);
        server.addAeropuerto("aaa", "aaa", "aaa");
        server.addTipoDeAvion(1, 1, 1, 1, 1, 1, 1, "a");
        server.addAvion("1", "a");
        server.addVuelo("aaa", "aaa", 1, 1, 2018, 22, 30, 1, "1", 3, 3);

        server.comprarAsiento(1, 3, server.getVuelo(3).getAsiento(1, 'A'), 1);

    }

    @Test
    public  void test03(){
        char a = 'a';
        int b = 1;

        System.out.println("" + b + a);
    }



}